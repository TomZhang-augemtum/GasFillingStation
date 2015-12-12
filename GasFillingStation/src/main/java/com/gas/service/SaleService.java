package com.gas.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gas.dao.CompanyDao;
import com.gas.model.Company;
import com.gas.model.CostHistory;
import com.gas.model.Sale;
import com.gas.model.SalePageableAndSort;;
@Service
public class SaleService {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public List<Sale> getCompany(SalePageableAndSort pageAndSort) {
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
        return resultList;

    }
}
// CriteriaQuery<Project> criteriaQuery =
// criteriaBuilder.createQuery(Project.class);
// Root<Project> root = criteriaQuery.from(Project.class);
// Join<Project, ProjectStatus> join = root.join("projectStatusses",
// JoinType.LEFT);
//
//// Create a subquery to get latest ProjectStatus for project
// Subquery sq = criteriaBuilder.createQuery().subquery(Long.class);
// Root<T> from = sq.from(Project.class);
// Path<ProjectStatus> path = root.join("projectStatusses");
//// Get latest status
// sq.select(criteriaBuilder.max(path.<Long>get("id")));
//// For each project
// sq.where(criteriaBuilder.equal(from.<Long>get("id"), root.<Long>get("id")));
// Predicate latestStatusCondition =
// criteriaBuilder.and(criteriaBuilder.equal(join.get("id"), sq));
//
// criteriaQuery.orderBy(criteriaBuilder.asc(join.get("statusType")));
// criteriaQuery.where(latestStatusCondition);