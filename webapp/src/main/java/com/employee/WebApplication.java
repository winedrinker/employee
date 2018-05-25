package com.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.employee.service.EmployeeServiceForWeb;

@SpringBootApplication
public class WebApplication {

    @Autowired
    private EmployeeServiceForWeb employeeService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebApplication.class, args);
    }
}
