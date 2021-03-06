package com.example.seraphine.model;

import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Query Builder class to create some operations to search doctor based on the input value
 * @author Vinh Truong Canh Thanh
 */

public class QueryBuilder<T> implements Specification<T> {
    private final List<SearchCriteria> criteria_list;

    public QueryBuilder() {
        this.criteria_list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        criteria_list.add(criteria);
    }

    /**
     * Using predicate to query the satisfied data in the database
     * @author Vinh Truong Canh Thanh
     */

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : criteria_list) {
            if (criteria.getOperation().equals(SearchOperation.GREATER_THAN)) {
                predicates.add(builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
            }
            else if (criteria.getOperation().equals(SearchOperation.LESS_THAN)) {
                predicates.add(builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
            }
            else if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
                predicates.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
            }
            else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
                predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
            }
            else if (criteria.getOperation().equals(SearchOperation.NOT_EQUAL)) {
                predicates.add(builder.notEqual(root.get(criteria.getKey()), criteria.getValue()));
            }
            else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
            }
            else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(builder.like(builder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%"));
            }
            else if (criteria.getOperation().equals(SearchOperation.MATCH_END)) {
                predicates.add(builder.like(builder.lower(root.get(criteria.getKey())), criteria.getValue().toString().toLowerCase() + "%"));
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }

}
