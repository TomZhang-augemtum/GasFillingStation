package com.gas.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gas.dao.CompanyDao;
import com.gas.dao.UserDao;
import com.gas.model.Company;
import com.gas.model.CostHistory;
import com.gas.model.Sale;
import com.gas.model.SalePageableAndSort;
import com.gas.model.User;;
@Service
public class SaleService {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public Page<Sale> getCompany(SalePageableAndSort pageAndSort) {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Sale> query = cb.createQuery(Sale.class);
        Root<CostHistory> root = query.from(CostHistory.class);
        query.multiselect(root.get("companyid").alias("id"),
                cb.sum(root.<Double> get("gasAmount")).alias("gasTotalNum"),
                cb.sum(root.<Double> get("total")).alias("moneyTotalNum"));
        query.groupBy(root.get("companyid"));
        String orderColumn = "gas".equals(pageAndSort.getOrderColumn()) ? "gasAmount" : "moneyTotalNum";
        if ("desc".equals(pageAndSort.getOrder())){
            query.orderBy(cb.desc(cb.sum(root.<Double> get(orderColumn))));
        } else {
            query.orderBy(cb.asc(cb.sum(root.<Double> get(orderColumn))));
        }
        Predicate condition = cb.and(cb.between(root.get("time"), pageAndSort.getFromDate(), pageAndSort.getToDate()));
        query.where(condition);
        query.getGroupRestriction();
        List<Sale> resultList = em.createQuery(query).setFirstResult(pageAndSort.getPage() * pageAndSort.getSize())
                .setMaxResults(pageAndSort.getSize()).getResultList();
        Company company = null;
        for (Sale sale : resultList) {
            company = companyDao.findOne(sale.getId());
            sale.setName(company.getName());
            sale.setLocation(company.getLocation());
        }
        CriteriaQuery<Long> queryCount = cb.createQuery(Long.class);
        root = queryCount.from(CostHistory.class);
        queryCount.where(condition);
        queryCount.select(cb.count(root));
        List<Long> totals = em.createQuery(queryCount).getResultList();
        Long total = 0L;

        for (Long element : totals) {
            total += element == null ? 0 : element;
        }

        return new PageImpl<Sale>(resultList, new PageRequest(pageAndSort.getPage(), pageAndSort.getSize(),
                new Sort(Direction.fromString(pageAndSort.getOrder()), pageAndSort.getOrder())), total);
    }

    public Page<Sale> getCompanyAll(Date begin, Date end) {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Sale> query = cb.createQuery(Sale.class);
        Root<CostHistory> root = query.from(CostHistory.class);
        query.multiselect(root.get("companyid").alias("id"),
                cb.sum(root.<Double> get("gasAmount")).alias("gasTotalNum"),
                cb.sum(root.<Double> get("total")).alias("moneyTotalNum"));
        Predicate condition = cb.and(cb.between(root.get("time"), begin, end));
        query.where(condition);
        query.groupBy(root.get("companyid"));
        query.getGroupRestriction();
        List<Sale> resultList = em.createQuery(query).getResultList();
        Company company = null;
        for (Sale sale : resultList) {
            company = companyDao.findOne(sale.getId());
            sale.setName(company.getName());
            sale.setLocation(company.getLocation());
        }
        return new PageImpl<Sale>(resultList);
    }

    public Page<Sale> getEmployee(SalePageableAndSort pageAndSort, Long
     companyid) {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Sale> query = cb.createQuery(Sale.class);
        Root<CostHistory> root = query.from(CostHistory.class);
        query.multiselect(root.get("userid").alias("id"), cb.sum(root.<Double> get("gasAmount")).alias("gasTotalNum"),
                cb.sum(root.<Double> get("total")).alias("moneyTotalNum"));
        query.groupBy(root.get("userid"));
        String orderColumn = "gas".equals(pageAndSort.getOrderColumn()) ? "gasAmount" : "moneyTotalNum";
        if ("desc".equals(pageAndSort.getOrder())) {
            query.orderBy(cb.desc(cb.sum(root.<Double> get(orderColumn))));
        } else {
            query.orderBy(cb.asc(cb.sum(root.<Double> get(orderColumn))));
        }
        Predicate condition1 = cb.and(cb.between(root.get("time"), pageAndSort.getFromDate(), pageAndSort.getToDate()));
        Predicate condition2 = cb.equal(root.get("companyid").as(Long.class), companyid);
        query.where(condition1, condition2);
        query.getGroupRestriction();
        List<Sale> resultList = em.createQuery(query).setFirstResult(pageAndSort.getPage() * pageAndSort.getSize())
                .setMaxResults(pageAndSort.getSize()).getResultList();
        User user = null;
        for (Sale sale : resultList) {
            user = userDao.findOne(sale.getId());
            sale.setName(user.getName());
            sale.setNumber(user.getNumber());
        }
        CriteriaQuery<Long> queryCount = cb.createQuery(Long.class);
        root = queryCount.from(CostHistory.class);
        queryCount.where(condition1, condition2);
        queryCount.select(cb.count(root));
        List<Long> totals = em.createQuery(queryCount).getResultList();
        Long total = 0L;

        for (Long element : totals) {
            total += element == null ? 0 : element;
        }

        return new PageImpl<Sale>(resultList, new PageRequest(pageAndSort.getPage(), pageAndSort.getSize(),
                new Sort(Direction.fromString(pageAndSort.getOrder()), pageAndSort.getOrder())), total);
    }

    public Page<Sale> getEmployeeAll(Long companyid, Date begin, Date end) {
        EntityManager em = entityManagerFactory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Sale> query = cb.createQuery(Sale.class);
        Root<CostHistory> root = query.from(CostHistory.class);
        query.multiselect(root.get("userid").alias("id"), cb.sum(root.<Double> get("gasAmount")).alias("gasTotalNum"),
                cb.sum(root.<Double> get("total")).alias("moneyTotalNum"));
        query.groupBy(root.get("userid"));
        Predicate condition = cb.and(cb.between(root.get("time"), begin, end));
        query.where(condition);
        query.getGroupRestriction();
        List<Sale> resultList = em.createQuery(query).getResultList();
        User user = null;
        for (Sale sale : resultList) {
            user = userDao.findOne(sale.getId());
            sale.setName(user.getName());
            sale.setNumber(user.getNumber());
        }
        CriteriaQuery<Long> queryCount = cb.createQuery(Long.class);
        root = queryCount.from(CostHistory.class);
        return new PageImpl<Sale>(resultList);
    }

}