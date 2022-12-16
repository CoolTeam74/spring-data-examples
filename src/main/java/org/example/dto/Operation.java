package org.example.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Operation {
    GR(">"),
    LO("<"),
    EQ("="),
    IN("IN");

    private final String alias;
}
