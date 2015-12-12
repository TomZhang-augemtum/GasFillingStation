package com.gas.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gas.model.Car;
import com.gas.model.Card;
import com.gas.model.CardType;
import com.gas.model.CostHistory;
import com.gas.model.RechargeHistory;
import com.gas.model.ReturnData;
import com.gas.model.Role;
import com.gas.model.User;
import com.gas.service.CarService;
import com.gas.service.CardService;
import com.gas.service.UserService;

@RestController
public class CardController {
    @Autowired
    private CardService cardService;
    @Autowired
    private UserService userService;
    @Autowired
    private CarService carService;

    @RequestMapping("/api/card/list")
    public List<Card> list(HttpServletRequest request, Model model) {
        return cardService.getList();
    }

    @RequestMapping("/api/card/list/pagenation")
    public Page<Card> listByPagenation(HttpServletRequest request, Model model) {
        Pageable page = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "id"));
        return cardService.getListByPagenationAndOrder(page);
    }

    @RequestMapping(value = "/api/card/save", method = RequestMethod.POST)
    public void save(HttpServletRequest request, User user, Car car) {
        CardType type = new CardType();
        type.setId(3L);

        UUID uuid = UUID.randomUUID();
        Card card = new Card();
        card.setBalance(0);
        card.setId(uuid.toString());
        card.setType(type);
        Card recard = cardService.save(card);

        carService.save(car);

        Role role = new Role();
        role.setId(3L);
        user.setRole(role);
        user.setCar(car);
        user.setCardid(uuid.toString());
        User res = userService.saveUser(user);

    }

    @RequestMapping("/api/card/delete")
    public void delete(HttpServletRequest request, Card card) {
        cardService.delete(card);
    }

    @RequestMapping("/api/card/ban")
    public void ban(HttpServletRequest request, String id) {
        cardService.ban(id);
    }

    @RequestMapping("/api/card/unban")
    public void unban(HttpServletRequest request, String id) {
        cardService.unban(id);
    }

    @RequestMapping("/api/card/one/idcard")
    public Card findone(HttpServletRequest request, String id) {
        return cardService.findone(id);
    }

    @RequestMapping("/api/card/cost")
    public ReturnData cost(HttpServletRequest request, CostHistory costHistory, String phone) {
        costHistory.setOperatorid(((User) request.getSession().getAttribute("user")).getId());
        return cardService.cost(costHistory, phone);
    }

    @RequestMapping("/api/card/recharge")
    public ReturnData recharge(HttpServletRequest request, Double money, String phone) {
        RechargeHistory rechargeHistory = new RechargeHistory();
        rechargeHistory.setOperatorid(((User) request.getSession().getAttribute("user")).getId());
        rechargeHistory.setMoney(money);
        return cardService.recharge(rechargeHistory, phone);
    }
}
