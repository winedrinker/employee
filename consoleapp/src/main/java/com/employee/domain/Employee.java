package com.employee.domain;

import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Employee implements Comparable<Employee> {

    @JsonDeserialize(using=NameDeserializer.class)
    @JsonProperty("name")
    private FullName fullName;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty("department")
    private Set<Department> departments;

    public FullName getFullName() {
        return fullName;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    @Override
    public int compareTo(final Employee o) {
        return this.getFullName().getSurnameName().compareTo(o.getFullName().getSurnameName());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Employee employee = (Employee) o;
        return Objects.equals(fullName, employee.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }
}
