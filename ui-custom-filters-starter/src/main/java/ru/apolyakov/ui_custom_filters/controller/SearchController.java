package ru.apolyakov.ui_custom_filters.controller;

import lombok.RequiredArgsConstructor;
import ru.apolyakov.ui_custom_filters.dto.Filter;
import ru.apolyakov.ui_custom_filters.dto.ResultDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.apolyakov.ui_custom_filters.service.SearchService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {
    private final SearchService searchService;

    @GetMapping
    public ResultDto search(@RequestParam("itemType") String itemType, @RequestParam("filter") Filter filter) {
        return searchService.search(filter, itemType);
    }
}
