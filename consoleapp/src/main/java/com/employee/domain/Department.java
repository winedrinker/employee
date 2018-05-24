package com.employee.domain;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Department {
    FINANCE("finance"),
    IT("it"),
    PACKAGING("packaging");

    private final String name;

    private Department(final String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }

    @JsonCreator
    public static Department forName(final String name) {
        return Arrays.stream(values())
            .filter(department -> name.equals(department.getName()))
            .findFirst()
            .orElse(null);
    }
}
