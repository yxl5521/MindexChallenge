package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationInput;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Jasmine Liang
 * @email: yxl5521@rit.edu
 * @date: 2022-02-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
    private String compUrl;
    private String compIdUrl;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compUrl = "http://localhost:" + port + "/compensation";
        compIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }

    @Test
    public void testCreateRead() {
        String testId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        Employee testEmployee = employeeService.read(testId);
        CompensationInput testComp = new CompensationInput();
        testComp.setSalary("100,000");
        testComp.setEmployeeId(testEmployee.getEmployeeId());
        testComp.setEffectiveDate("2022-01-17");
        LocalDate localDate = LocalDate.parse("2022-01-17");
        // Create checks
        Compensation createdComp = restTemplate.postForEntity(compUrl, testComp, Compensation.class).getBody();

        assertNotNull(createdComp.getEmployee());
        assertEquals(testEmployee, createdComp.getEmployee());
        assertEquals(testComp.getSalary(), createdComp.getSalary());
        assertEquals(testComp.getEffectiveDate(), createdComp.getEffectiveDate().toString());

        // Read checks
        Compensation readComp = restTemplate.getForEntity(compIdUrl, Compensation.class, testId).getBody();
        assertNotNull(readComp);
        assertNotNull(readComp.getEmployee());
        assertEquals(createdComp.getEmployee(), readComp.getEmployee());
        assertEquals(createdComp.getSalary(), readComp.getSalary());
        assertEquals(createdComp.getEffectiveDate(), readComp.getEffectiveDate());

    }
}
