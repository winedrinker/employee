package com.employee.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.employee.dao.EmployeeRepository;
import com.employee.domain.Department;
import com.employee.domain.Employee;
import com.employee.domain.FullName;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceForWebTest {

    private EmployeeServiceForWeb underTest;

    @Mock
    private EmployeeRepository repositoryMock;

    @Before
    public void setup() {
        underTest = new EmployeeServiceForWeb(repositoryMock);
    }

    @Test
    public void a() {

        underTest.getAllFullNameSorted();
    }
}
