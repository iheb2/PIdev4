package com.github.adminfaces.starter.repository;

import org.springframework.data.domain.Page;

import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.model.Agency;
import com.github.adminfaces.starter.model.AgencyPage;
import com.github.adminfaces.starter.model.Agencysearchcriterea;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Repository
public class AgencyCriteriaRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public AgencyCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Agency> findAllWithFilters(AgencyPage agencyPage,
    		 Agencysearchcriterea agencysearchcriterea){
        CriteriaQuery<Agency> criteriaQuery = criteriaBuilder.createQuery(Agency.class);
        Root<Agency> agencyRoot = criteriaQuery.from(Agency.class);
        Predicate predicate = getPredicate(agencysearchcriterea, agencyRoot);
        criteriaQuery.where(predicate);
        setOrder(agencyPage, criteriaQuery, agencyRoot);

        TypedQuery<Agency> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(agencyPage.getPageNumber() * agencyPage.getPageSize());
        typedQuery.setMaxResults(agencyPage.getPageSize());

        Pageable pageable = getPageable(agencyPage);

        long employeesCount = getagenciesCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, employeesCount);
    }

    private Predicate getPredicate(Agencysearchcriterea agencysearchcriterea,
                                   Root<Agency> agencyRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(agencysearchcriterea.getEmail())){
            predicates.add(
                    criteriaBuilder.like(agencyRoot.get("email"),
                            "%" + agencysearchcriterea.getEmail()+ "%")
            );
        }
        if(Objects.nonNull(agencysearchcriterea.getPhone())){
            predicates.add(
                    criteriaBuilder.like(agencyRoot.get("phone"),
                            "%" + agencysearchcriterea.getPhone() + "%")
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(AgencyPage agencyPage,
                          CriteriaQuery<Agency> criteriaQuery,
                          Root<Agency> employeeRoot) {
        if(agencyPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(employeeRoot.get(agencyPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(employeeRoot.get(agencyPage.getSortBy())));
        }
    }

    private Pageable getPageable(AgencyPage agencyPage) {
        Sort sort = Sort.by(agencyPage.getSortDirection(), agencyPage.getSortBy());
        return PageRequest.of(agencyPage.getPageNumber(),agencyPage.getPageSize(), sort);
    }

    private long getagenciesCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Agency> countRoot = countQuery.from(Agency.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
