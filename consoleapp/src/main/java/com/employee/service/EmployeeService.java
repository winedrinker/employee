package com.employee.service;

import com.employee.dao.EmployeeRepository;
import com.employee.domain.Department;
import com.employee.domain.Employee;
import com.employee.domain.FullName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.toList;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Gets all {@link Employee}s grouped by their {@link Department}s.
     * @return a map which contains departments as keys and the related employees as values
     */
    public Map<Department, List<FullName>> getByDepartment() {

        final List<Employee> allEmployees = employeeRepository.getFromJsonFile();
        final Map<Department, List<FullName>> byDepartmentMap = new TreeMap<>();

        Arrays.stream(Department.values()).forEachOrdered(
            department -> byDepartmentMap.put(department, allEmployees.stream().distinct().sorted().filter(employee -> employee.getDepartments().contains(department))
                .map(Employee::getFullName).collect(toList()))
        );

        return byDepartmentMap;
    }
}
