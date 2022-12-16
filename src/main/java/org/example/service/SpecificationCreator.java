package org.example.service;

import lombok.experimental.UtilityClass;
import org.example.dto.AndFilter;
import org.example.dto.Filter;
import org.example.dto.OrFilter;
import org.example.dto.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@UtilityClass
public class SpecificationCreator {
    public <T> Specification<T> create(Filter filter) {
        if (filter instanceof OrFilter) {
            OrFilter orFilter = (OrFilter) filter;
            List<Specification<T>> innerSpecifications = createInnerSpecifications(orFilter.getValue());
            return createOrSpecification(innerSpecifications);
        } else if (filter instanceof AndFilter) {
            return null;
        } else {
            SearchCriteria searchCriteria = (SearchCriteria) filter;
            return createSearchSpecification(searchCriteria);
        }
    }

    private <T> Specification<T> createSearchSpecification(SearchCriteria searchCriteria) {
        return new SearchSpecification<>(searchCriteria);
    }

    private <T> Specification<T> createOrSpecification(List<Specification<T>> innerSpecifications) {
        Specification<T> orSpecification =Specification.where(innerSpecifications.get(0));
        for (int i = 1; i < innerSpecifications.size(); i++) {
            orSpecification = innerSpecifications.get(i).or(orSpecification);
        }
        return orSpecification;
    }

    private <T> List<Specification<T>> createInnerSpecifications(List<Filter> value) {
        List<Specification<T>> specs = Arrays.asList();
        if (!CollectionUtils.isEmpty(value)) {
            value.forEach(f -> {
                Specification<T> specification = create(f);
                specs.add(specification);
            });
        }
        return specs;
    }
}
