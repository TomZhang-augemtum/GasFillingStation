package com.gas.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gas.dao.CardDao;
import com.gas.dao.CostHistoryDao;
import com.gas.dao.RechargeHistoryDao;
import com.gas.dao.UserDao;
import com.gas.model.Card;
import com.gas.model.CardBalanceHistory;
import com.gas.model.CardState;
import com.gas.model.CostHistory;
import com.gas.model.RechargeHistory;
import com.gas.model.ReturnData;
import com.gas.model.User;
import com.gas.model.WechatConfig;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpMessage;;
@Service
public class CardService {

    @Autowired
    private CardDao cardDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CostHistoryDao costHistoryDao;
    @Autowired
    private RechargeHistoryDao rechargeHistoryDao;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public List<Card> getList() {
        List<Card> cards = cardDao.findAll();
        for (Card card : cards) {
            card.setUser(userDao.findByCardid(card.getId()));
        }
        return cards;
    }

    public Card save(Card card) {
        return cardDao.save(card);
    }

    public void delete(Card card) {
        cardDao.delete(card);
    }

    public void ban(String id) {
        Card card = cardDao.findOne(id);
        CardState state = new CardState();
        state.setId(2L);
        card.setState(state);
        cardDao.save(card);
    }

    public void unban(String id) {
        Card card = cardDao.findOne(id);
        CardState state = new CardState();
        state.setId(1L);
        card.setState(state);
        cardDao.save(card);
    }

    public Page<Card> getListByPagenationAndOrder(Pageable page) {
        Page<Card> cards = cardDao.findAll(page);
        for (Card card : cards.getContent()) {
            card.setUser(userDao.findByCardid(card.getId()));
        }
        return cards;
//        return cards.getContent();
    }

    public Card findoneByUserIDcard(String id) {
        User user = userDao.findByIdcard(id);
        Card card = cardDao.findOne(user.getCardid());
        card.setUser(user);
        return card;
    }

    public ReturnData cost(CostHistory costHistory, String phone) {
        ReturnData reData = new ReturnData();
        User user = userDao.findByPhone(phone);
        if (user == null) {
            reData.setCode("error");
            reData.setMessage("手机号输入有误,请确认！");
            return reData;
        }
        Card card = cardDao.findOne(user.getCardid());
        Double balance = card.getBalance();
        Double cost = costHistory.getGasAmount() * costHistory.getPrice();
        if (balance<cost) {
            reData.setCode("error");
            reData.setMessage("余额" + balance + ",不足本次消费,请充值");
            return reData;
        } else {
            card.setBalance(balance - cost);
            cardDao.save(card);
            costHistory.setBalance(balance - cost);
            costHistory.setTotal(costHistory.getPrice() * costHistory.getGasAmount());
            costHistory.setUserid(user.getId());
            costHistory.setCompanyid(user.getCompany().getId());
            costHistory.setTime(new Date());
            costHistoryDao.save(costHistory);
            reData.setCode("success");
            reData.setMessage("余额:" + (balance - cost) + ",本次消费:" + cost);
            WxCpMessage message = WxCpMessage.TEXT()
                    .agentId(WechatConfig.getWechatConfig().getWxCpInMemoryConfigStorage().getAgentId()) // 企业号应用ID
                    .toUser(user.getNumber()).content("您的余额:" + (balance - cost) + ",本次消费:" + cost).build();

            try {
                WechatConfig.getWechatConfig().getWxCpService().messageSend(message);
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
            return reData;
        }
    }

    public ReturnData scancost(CostHistory costHistory, String cardid) {
        ReturnData reData = new ReturnData();
        Card card = cardDao.findOne(cardid);
        if (card == null) {
            reData.setCode("error");
            reData.setMessage("此卡不存在,请确认！");
            return reData;
        }
        if (card.getState().getId() != 1) {
            reData.setCode("error");
            reData.setMessage("此卡已" + card.getState().getName() + ",请确认！");
            return reData;
        }
        User user = userDao.findByCardid(cardid);

        Double balance = card.getBalance();
        Double cost = costHistory.getGasAmount() * costHistory.getPrice();
        if (balance < cost) {
            reData.setCode("error");
            reData.setMessage("余额" + balance + ",不足本次消费,请充值");
            return reData;
        } else {
            card.setBalance(balance - cost);
            cardDao.save(card);
            costHistory.setBalance(balance - cost);
            costHistory.setTotal(costHistory.getPrice() * costHistory.getGasAmount());
            costHistory.setUserid(user.getId());
            costHistory.setCompanyid(user.getCompany().getId());
            costHistory.setTime(new Date());
            costHistoryDao.save(costHistory);
            reData.setCode("success");
            reData.setMessage("余额:" + (balance - cost) + ",本次消费:" + cost);
            WxCpMessage message = WxCpMessage.TEXT()
                    .agentId(WechatConfig.getWechatConfig().getWxCpInMemoryConfigStorage().getAgentId()) // 企业号应用ID
                    .toUser(user.getNumber()).content("您的余额:" + (balance - cost) + ",本次消费:" + cost)
                    .build();
            try {
                WechatConfig.getWechatConfig().getWxCpService().messageSend(message);
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
            return reData;
        }
    }


    public ReturnData recharge(RechargeHistory rechargeHistory, String phone) {
        ReturnData reData = new ReturnData();
        User user = userDao.findByPhone(phone);
        if (user == null) {
            reData.setCode("error");
            reData.setMessage("手机号输入有误,请确认！");
            return reData;
        }
        Card card = cardDao.findOne(user.getCardid());
        Double balance = card.getBalance();
        Double money = rechargeHistory.getMoney();
        card.setBalance(balance + money);
        cardDao.save(card);
        rechargeHistory.setCardid(card.getId());
        rechargeHistory.setTime(new Date());
        rechargeHistory.setBalance(balance);
        rechargeHistoryDao.save(rechargeHistory);

        reData.setCode("success");
        reData.setMessage("余额:" + (balance + money) + ",本次充值:" + money);
        WxCpMessage message = WxCpMessage.TEXT()
                .agentId(WechatConfig.getWechatConfig().getWxCpInMemoryConfigStorage().getAgentId()) // 企业号应用ID
                .toUser(user.getNumber()).content("余额:" + (balance + money) + ",本次充值:" + money).build();
        try {
            WechatConfig.getWechatConfig().getWxCpService().messageSend(message);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return reData;
    }

    public Card findone(String cardid) {
        return cardDao.findOne(cardid);
    }

    // public Page<CardBalanceHistory> getHistory(User user, SalePageableAndSort
    // pageAndSort) {
    public Page<CardBalanceHistory> getHistory(User user, Pageable pageable) {
        EntityManager em = entityManagerFactory.createEntityManager();
        String sql = "select * from ((select 'recharge' as type,time,balance,money from rechargehistory where cardid='"
                + user.getCardid()
                + "') union (select 'cost' as type,time, balance, total as money from costhistory where userid = '"
                + user.getId() + "')) result order by time desc limit " + pageable.getOffset() + ",10";
        List<CardBalanceHistory> result = em.createNativeQuery(sql, CardBalanceHistory.class).getResultList();
        String sql2 = "select count(1) from ((select 'recharge' as type,time,balance,money from rechargehistory where cardid='"
                + user.getCardid()
                + "') union (select 'cost' as type,time, balance, total as money from costhistory where userid = '"
                + user.getId() + "')) result";
        BigInteger total = (BigInteger) em.createNativeQuery(sql2).getResultList().get(0);
        return new PageImpl<CardBalanceHistory>(result, pageable, total.intValue());
    }

    public Card findoneByIdCard(String id) {
        User user = userDao.findByIdcard(id);
        return cardDao.findOne(user.getCardid());
    }
}
