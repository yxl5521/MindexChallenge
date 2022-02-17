package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationInput;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Jasmine Liang
 * @email: yxl5521@rit.edu
 * @date: 2022-02-17
 */
@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeService employeeService;

    /**
     * Save new compensation for employee into the db
     * @param compensation The compensation detail to save
     * @return The stored compensation
     */
    @Override
    public Compensation create(CompensationInput compensation) {
        LOG.debug("Creating compensation for employee");
        String id = compensation.getEmployeeId();
        Employee employee = employeeService.read(id);
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        Compensation comp = new Compensation();
        comp.setEmployee(employee);
        comp.setSalary(compensation.getSalary());
        // TODO: Make sure the need for different timezone
        // If need to deal with time zone, need to also save the offset in db
        ZoneId timeZone = ZoneId.systemDefault();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(compensation.getEffectiveDate(),
                formatter);
        comp.setEffectiveDate(date);
        compensationRepository.insert(comp);
        return comp;
    }

    /**
     * Get the compensation using employee id
     * @param id The employee Id
     * @return The retrieved compensation
     */
    @Override
    public Compensation read(String id) {
        LOG.debug("Get compensation with id [{}]", id);

        Compensation compensation = compensationRepository.findByEmployeeId(id);

        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }
        return compensation;
    }

}
