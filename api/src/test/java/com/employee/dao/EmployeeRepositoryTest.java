package com.employee.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.refEq;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.employee.domain.Employee;
import com.employee.domain.EmployeeWrapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeRepositoryTest {

    private EmployeeRepository underTest;

    @Mock
    private ObjectMapper objectMapperMock;
    @Mock
    private ObjectReader objectReaderMock;
    @Mock
    private EmployeeWrapper employeeWrapperMock;

    @Before
    public void setup() {
        underTest = new EmployeeRepository(objectMapperMock);
    }

    @Test
    public void getFromJsonFileShouldReturnTheExpectedListOfEmployees() throws IOException {
        // GIVEN
        final List<Employee> expected = new ArrayList<>();
        given(objectMapperMock.readerFor(any(TypeReference.class))).willReturn(objectReaderMock);
        given(objectReaderMock.with(DeserializationFeature.UNWRAP_ROOT_VALUE)).willReturn(objectReaderMock);
        given(objectReaderMock.readValue(any(InputStream.class))).willReturn(employeeWrapperMock);
        given(employeeWrapperMock.getEmployees()).willReturn(expected);
        // WHEN
        final List<Employee> actual = underTest.getFromJsonFile();
        // THEN
        assertSame(expected, actual);
    }
}
