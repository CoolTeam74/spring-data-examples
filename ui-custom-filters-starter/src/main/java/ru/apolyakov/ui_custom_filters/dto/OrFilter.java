package ru.apolyakov.ui_custom_filters.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrFilter {
    private final String type = "OR";

    private List<Filter> value;
}
