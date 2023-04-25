package com.ccsw.ccswmanager.intern;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ccsw.ccswmanager.common.SearchCriteria;
import com.ccsw.ccswmanager.intern.model.InternEntity;

public class InternSpecification implements Specification<InternEntity> {

    private SearchCriteria criteria;

    public InternSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<InternEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()).as(Date.class),
                    (Date) criteria.getFirstValue());
        } else if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()).as(Date.class),
                    (Date) criteria.getFirstValue());
        } else if (criteria.getOperation().equalsIgnoreCase("<>")) {
            return builder.between(root.get(criteria.getKey()).as(Date.class), (Date) criteria.getFirstValue(),
                    (Date) criteria.getSecondValue());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getFirstValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getFirstValue());
            }
        }

        return null;
    }

}