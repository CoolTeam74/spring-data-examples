package org.example.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrFilter {
    private final String type = "OR";

    private List<Filter> value;
}
