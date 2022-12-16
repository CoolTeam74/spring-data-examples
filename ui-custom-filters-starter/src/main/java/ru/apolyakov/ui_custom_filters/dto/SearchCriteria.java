package ru.apolyakov.ui_custom_filters.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchCriteria implements Filter {
    private final String type = "SEARCH";

    private String key;

    private String operator;

    private Object value;

    private String table;
}
