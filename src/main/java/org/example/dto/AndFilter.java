package org.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class AndFilter {
    private final String type = "AND";

    private List<Filter> value;
}
