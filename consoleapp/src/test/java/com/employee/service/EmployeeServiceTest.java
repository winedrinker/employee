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
public class EmployeeServiceTest {

    private EmployeeService underTest;

    @Mock
    private EmployeeRepository repositoryMock;

    @Before
    public void setup() {
        underTest = new EmployeeService(repositoryMock);
    }

    @Test
    public void getByDepartmentShouldReturnTheExpectedMap() {
        // GIVEN
        final List<Employee> expectedEmployees = createEmployees();
        given(repositoryMock.getFromJsonFile()).willReturn(expectedEmployees);
        final Map<Department, List<FullName>> expectedMap = createExpectedMap(expectedEmployees);
        // WHEN
        final Map<Department, List<FullName>> actual = underTest.getByDepartment();
        // THEN
        assertEquals(expectedMap, actual);
    }

    private Map<Department, List<FullName>> createExpectedMap(final List<Employee> expectedEmployees) {
        final Map<Department, List<FullName>> expectedMap = new TreeMap<>();
        expectedMap.computeIfAbsent(Department.FINANCE, l -> new ArrayList<>()).add(expectedEmployees.get(0).getFullName());
        expectedMap.put(Department.IT, Arrays.asList(expectedEmployees.get(2).getFullName()));
        expectedMap.put(Department.PACKAGING, Arrays.asList(expectedEmployees.get(1).getFullName(), expectedEmployees.get(2).getFullName()));
        return expectedMap;
    }

    private ArrayList<Employee> createEmployees() {

        Employee emp1 = new Employee();
        emp1.setFullName(new FullName("George", "Lucas"));
        emp1.setDepartments(new HashSet<>(Arrays.asList(Department.FINANCE)));
        Employee emp2 = new Employee();
        emp2.setFullName(new FullName("John", "Smith"));
        emp2.setDepartments(new HashSet<>(Arrays.asList(Department.IT, Department.PACKAGING)));
        Employee emp3 = new Employee();
        emp3.setFullName(new FullName("Johny", "Bean"));
        emp3.setDepartments(new HashSet<>(Arrays.asList(Department.PACKAGING)));

        return new ArrayList<>(Arrays.asList(emp1, emp3, emp2));
    }
}
