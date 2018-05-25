package com.employee;

import java.util.List;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.employee.domain.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebApplicationIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testListEmployeeNamesSorted() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/employee/sort"),
            HttpMethod.GET, entity, String.class);

        String expected = "[{\"firstName\":\"James\",\"surnameName\":\"Doyle\"},{\"firstName\":\"Peter\",\"surnameName\":\"Goeking\"},{\"firstName\":\"Dale\",\"surnameName\":\"Miller\"},{\"firstName\":\"Joanne\",\"surnameName\":\"Olsen\"},{\"firstName\":\"Samuel\",\"surnameName\":\"Palmisano\"},{\"firstName\":\"George\",\"surnameName\":\"Smith\"},{\"firstName\":\"Michael\",\"surnameName\":\"Smith\"}]";

        JSONAssert.assertEquals(expected, response.getBody(), true);
    }

    @Test
    public void testListEmployeesForDepartmentSorted() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/employee/james/doyle/department"),
            HttpMethod.GET, entity, String.class);

        String expected = "[\"PACKAGING\",\"PACKAGING\"]";

        JSONAssert.assertEquals(expected, response.getBody(), true);

    }

    @Test
    public void listEmployeesForDepartmentSorted() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
            createURLWithPort("/department/finance/employees"),
            HttpMethod.GET, entity, String.class);

        String expected = "[{\"name\":{\"firstName\":\"Peter\",\"surnameName\":\"Goeking\"},\"department\":[\"FINANCE\"]},{\"name\":{\"firstName\":\"Dale\",\"surnameName\":\"Miller\"},\"department\":[\"PACKAGING\",\"FINANCE\"]},{\"name\":{\"firstName\":\"Joanne\",\"surnameName\":\"Olsen\"},\"department\":[\"FINANCE\"]},{\"name\":{\"firstName\":\"George\",\"surnameName\":\"Smith\"},\"department\":[\"FINANCE\"]},{\"name\":{\"firstName\":\"Michael\",\"surnameName\":\"Smith\"},\"department\":[\"IT\",\"FINANCE\"]}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
