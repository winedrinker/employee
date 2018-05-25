package com.employee.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.domain.Department;
import com.employee.domain.Employee;
import com.employee.domain.FullName;
import com.employee.service.EmployeeServiceForWeb;

@RestController
@EnableAutoConfiguration
public class EmployeeController {

    private EmployeeServiceForWeb employeeService;

    @Inject
    public EmployeeController(EmployeeServiceForWeb employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee/sort")
    @ResponseBody
    public List<FullName> listEmployeeNamesSorted() {
        return employeeService.getAllFullNameSorted();
    }

    @GetMapping("/employee/{employee-first-name}/{employee-last-name}/department")
    @ResponseBody
    public List<Department> listEmployeesForDepartmentSorted(@PathVariable("employee-first-name") String firstName, @PathVariable("employee-last-name") String lastName) {
        return employeeService.getDepartmentsForEmployee(new FullName(firstName, lastName));
    }

    @GetMapping("/department/{department-name}/employees")
    @ResponseBody
    public List<Employee> listEmployeesForDepartmentSorted(@PathVariable("department-name") String department) {
        return employeeService.getEmployeesForDepartment(Department.forName(department));
    }
}
