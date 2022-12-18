package ru.apolyakov.ui_custom_filters.service;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import ru.apolyakov.ui_custom_filters.dto.Operation;
import ru.apolyakov.ui_custom_filters.dto.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RequiredArgsConstructor
public class SearchSpecification<T> implements Specification<T> {
    private final SearchCriteria searchCriteria;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Join<Object, Object> join = null;
        if (searchCriteria.getTable() != null) {
            join = root.join(searchCriteria.getTable());
        }
        if (Operation.GR.name()
                .equalsIgnoreCase(searchCriteria.getOperator())) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.<String> get(searchCriteria.getKey()),
                    searchCriteria.getValue().toString());
        }
        else if (Operation.LO.name()
                .equalsIgnoreCase(searchCriteria.getOperator())) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.<String> get(searchCriteria.getKey()),
                    searchCriteria.getValue().toString());
        }
        else if (Operation.EQ.name()
                .equalsIgnoreCase(searchCriteria.getOperator())) {
            if (searchCriteria.getTable() != null) {
                if (join.get(searchCriteria.getKey()).getJavaType() == String.class) {
                    return criteriaBuilder.like(
                            criteriaBuilder.lower(join.get(searchCriteria.getKey())),
                            "%" + searchCriteria.getValue().toString().toLowerCase() + "%");
                } else {
                    return criteriaBuilder.equal(join.get(searchCriteria.getKey()),
                            searchCriteria.getValue());
                }
            } else {
                if (root.get(searchCriteria.getKey()).getJavaType() == String.class) {
                    return criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(searchCriteria.getKey())),
                            "%" + searchCriteria.getValue().toString().toLowerCase() + "%");
                } else {
                    return criteriaBuilder.equal(root.get(searchCriteria.getKey()),
                            searchCriteria.getValue());
                }
            }
        } else if (Operation.IN.name()
                .equalsIgnoreCase(searchCriteria.getOperator())) {
            List<String> values =
                    Lists.newArrayList((List<String>)searchCriteria.getValue());
            if (searchCriteria.getTable() != null) {
                return join.get(searchCriteria.getKey()).in(values);
            } else {
                return root.get(searchCriteria.getKey()).in(values);
            }
        }
        return null;
    }
}
