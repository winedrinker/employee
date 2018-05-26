package com.employee.service;

import com.employee.dao.EmployeeRepository;
import com.employee.domain.Department;
import com.employee.domain.Employee;
import com.employee.domain.FullName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Service to collect different employee related collections.
 */
@Service
public class EmployeeServiceForWeb {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceForWeb(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Collects all {@link FullName}s sorted by last name.
     * @return list of full names
     */
    public List<FullName> getAllFullNamesSorted() {

        final List<Employee> allEmployees = employeeRepository.getFromJsonFile();
        return allEmployees.stream().distinct().sorted().map(Employee::getFullName).collect(Collectors.toList());
    }

    /**
     * Gets all {@link Employee}s for the given {@link Department}.
     * @param department the department to filter
     * @return list of employees
     */
    public List<Employee> getEmployeesForDepartment(final Department department) {

        final List<Employee> allEmployees = employeeRepository.getFromJsonFile();
        return allEmployees.stream().distinct().sorted().filter(emp -> emp.getDepartments().contains(department)).collect(Collectors.toList());
    }

    /**
     * Collects all {@link Department}s for the given {@link FullName}.
     * @param fullName the full name of the employee
     * @return list of departments
     */
    public List<Department> getDepartmentsForEmployee(final FullName fullName) {

        final List<Employee> allEmployees = employeeRepository.getFromJsonFile();
        return allEmployees.stream().filter(employee -> employee.getFullName().equals(fullName)).map(Employee::getDepartments).flatMap(Set::stream).sorted().collect(
            Collectors.toList());
    }
}
