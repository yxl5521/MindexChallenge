package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Jasmine Liang
 * @email: yxl5521@rit.edu
 * @date: 2022-02-17
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportStructureServiceImplTest {

    private String structureUrl;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        structureUrl = "http://localhost:" + port + "/structure/{id}";
    }

    @Test
    public void testRead() {
        String testId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        Employee testEmployee = employeeService.read(testId);
        // Read checks
        ReportingStructure structure = restTemplate.getForEntity(structureUrl, ReportingStructure.class,
                testId).getBody();
        assertNotNull(structure);
        assertEquals(testEmployee.getEmployeeId(), structure.getEmployee().getEmployeeId());
        assertEquals(4, structure.getNumberOfReports());
    }

}
