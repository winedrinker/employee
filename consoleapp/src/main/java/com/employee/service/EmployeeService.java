package com.employee.service;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.domain.Department;
import com.employee.domain.Employee;
import com.employee.domain.EmployeeWrapper;
import com.employee.domain.FullName;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmployeeService {

    @Autowired
    private ObjectMapper objectMapper;

    public List<Employee> getAll() {
        return getFromJsonFile();
    }

    public Map<Department, List<FullName>> getByDepartment() {

        final List<Employee> allEmployees = getFromJsonFile();
        final Map<Department, List<FullName>> byDepartmentMap = new TreeMap<>();

        Arrays.stream(Department.values()).forEachOrdered(
            department ->  byDepartmentMap.put(department, allEmployees.stream().distinct().sorted().filter(employee -> employee.getDepartments().contains(department)).map(Employee::getFullName).collect(toList()))
        );

        return byDepartmentMap;
    }

    List<Employee> getFromJsonFile() {

        EmployeeWrapper wrapper = null;
        TypeReference<EmployeeWrapper> typeReference = new TypeReference<EmployeeWrapper>() {
        };
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("employee.json");
        try {
            wrapper = objectMapper.readerFor(typeReference).with(DeserializationFeature.UNWRAP_ROOT_VALUE).readValue(inputStream);
        } catch (IOException e) {
            System.out.println("Unable to load employees: " + e.getMessage());
        }

        return wrapper.getEmployees();
    }
}
