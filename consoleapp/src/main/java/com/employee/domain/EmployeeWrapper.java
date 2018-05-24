package com.employee.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("list")
public class EmployeeWrapper {

    @JsonProperty("employee")
    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }
}
