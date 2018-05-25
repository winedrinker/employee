package com.employee.domain;

import java.util.Objects;

public class FullName {

    private final String firstName;
    private final String surnameName;

    public FullName(final String firstName, final String surnameName) {
        this.firstName = firstName;
        this.surnameName = surnameName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurnameName() {
        return surnameName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FullName fullName = (FullName) o;
        return Objects.equals(firstName, fullName.firstName) &&
            Objects.equals(surnameName, fullName.surnameName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, surnameName);
    }

    @Override
    public String toString() {
        return String.format("%s %s", firstName, surnameName);
    }
}
