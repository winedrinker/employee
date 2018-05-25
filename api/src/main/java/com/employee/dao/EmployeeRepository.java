package com.employee.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.employee.domain.Employee;
import com.employee.domain.EmployeeWrapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EmployeeRepository {

    private final ObjectMapper objectMapper;

    @Inject
    public EmployeeRepository(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Employee> getFromJsonFile() {

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
