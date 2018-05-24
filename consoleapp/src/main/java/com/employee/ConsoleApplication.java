package com.employee;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.employee.domain.Department;
import com.employee.domain.FullName;
import com.employee.service.EmployeeService;

@SpringBootApplication
public class ConsoleApplication implements CommandLineRunner {

    @Autowired
    private EmployeeService employeeService;

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(ConsoleApplication.class);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("==================================================================================================");
        System.out.println();
        System.out.println("Employees by departments:");
        System.out.println();
        employeeService.getByDepartment().forEach(
            (department, fullNames) -> {
                System.out.println(department);
                System.out.println(fullNames);
                System.out.println();
            }
        );
        System.out.println();
        System.out.println("==================================================================================================");
    }
}
