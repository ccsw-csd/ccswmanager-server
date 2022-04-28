package com.capgemini.ccsw.ccswmanager.scholar;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.capgemini.ccsw.ccswmanager.scholar.model.SearchCriteria;
import com.capgemini.ccsw.ccswmanager.scholar.model.VScholarEntity;

/**
 * @author jchengli
 *
 */

public class VScholarSpecification implements Specification<VScholarEntity> {
    private SearchCriteria criteria;

    public VScholarSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<VScholarEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()).as(Date.class), (Date) criteria.getValue());
        } else if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()).as(Date.class), (Date) criteria.getValue());
        } else if (criteria.getOperation().equalsIgnoreCase("<>")) {
            return builder.between(root.get(criteria.getKey()).as(Date.class), (Date) criteria.getValue(),
                    (Date) criteria.getValue());
        }

        return null;
    }

}