package com.employee.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import com.employee.domain.Department;
import com.employee.domain.Employee;
import com.employee.domain.FullName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.employee.dao.EmployeeRepository;

import java.util.*;

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
    public void getAllFullNamesSortedShouldReturnAllNamesProperly() {
        // GIVEN
        List<Employee> allEmployees = createEmployees();
        final List<FullName> expectedFullNames = Arrays.asList(allEmployees.get(3).getFullName(), allEmployees.get(1).getFullName(), allEmployees.get(0).getFullName(), allEmployees.get(2).getFullName());
        given(repositoryMock.getFromJsonFile()).willReturn(allEmployees);
        // WHEN
        final List<FullName> actual = underTest.getAllFullNamesSorted();
        // THEN
        assertEquals(expectedFullNames, actual);
    }

    @Test
    public void getEmployeesForDepartmentShouldReturnExpectedEmployees() {
        // GIVEN
        List<Employee> allEmployees = createEmployees();
        final List<Employee> expectedEmployees = Arrays.asList(allEmployees.get(3), allEmployees.get(2));
        given(repositoryMock.getFromJsonFile()).willReturn(allEmployees);
        // WHEN
        final List<Employee> actual = underTest.getEmployeesForDepartment(Department.IT);
        // THEN
        assertEquals(expectedEmployees, actual);
    }

    @Test
    public void getDepartmentsForEmployeeShouldReturnExpectedDepartments() {
        // GIVEN
        List<Employee> allEmployees = createEmployees();
        final List<Department> expectedDepartments = Arrays.asList(Department.IT, Department.PACKAGING);
        given(repositoryMock.getFromJsonFile()).willReturn(allEmployees);
        // WHEN
        final List<Department> actual = underTest.getDepartmentsForEmployee(allEmployees.get(2).getFullName());
        // THEN
        assertEquals(expectedDepartments, actual);
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
        Employee emp4 = new Employee();
        emp4.setFullName(new FullName("Henry", "Answer"));
        emp4.setDepartments(new HashSet<>(Arrays.asList(Department.IT)));

        return new ArrayList<>(Arrays.asList(emp1, emp3, emp2, emp4));
    }
}
