package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Jasmine Liang
 * @email: yxl5521@rit.edu
 * @date: 2022-02-17
 */
public class ReportingStructureServiceImpl implements ReportingStructureService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * Get the report structure for an employee
     * @param id The employee id of the employee that need the report structure
     * @return The report structure
     */
    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Get employee structure with id [{}]", id);

        Employee employee = employeeService.read(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        int totalReports = employeeService.getTotalReports(id);

        ReportingStructure structure = new ReportingStructure();
        structure.setEmployee(employee);
        structure.setNumberOfReports(totalReports);
        return structure;
    }
}
