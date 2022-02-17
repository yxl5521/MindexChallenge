package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    /**
     * Get the total number of reports under the employee
     * @param id The id of the employee to check
     * @return The total number of employees under the current employee
     */
    @Override
    public int getTotalReports(String id){
        try {
            Employee employee = employeeRepository.findByEmployeeId(id);
            if (employee == null) {
                throw new RuntimeException("Invalid employeeId: " + id);
            }
            List<Employee> directReports = employee.getDirectReports();
            int totalReports = 0;
            if((directReports != null) && (!directReports.isEmpty())){
                for(Employee direct: directReports){
                    totalReports += 1 + getTotalReports(direct.getEmployeeId());
                }
            }
            return totalReports;
        } catch (Exception e){
            e.printStackTrace();
            LOG.error(e.getMessage());
        }
        return 0;
    }
}
