package ru.apolyakov.ui_custom_filters.service;

import lombok.RequiredArgsConstructor;
import ru.apolyakov.ui_custom_filters.dto.Filter;
import ru.apolyakov.ui_custom_filters.dto.ResultDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final RepositoryProvider repositoryProvider;

    public ResultDto search(Filter filter, String itemType) {
        Pageable pageable = Pageable.unpaged();
        JpaSpecificationExecutor repository = repositoryProvider.getRepository(itemType);
        Specification specification = SpecificationCreator.create(filter);
        Page results = repository.findAll(specification, pageable);
        return new ResultDto();
    }
}
