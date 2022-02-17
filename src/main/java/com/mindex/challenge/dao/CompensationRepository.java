package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Jasmine Liang
 * @email: yxl5521@rit.edu
 * @date: 2022-02-17
 */
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    @Query("{employee.$employeeId: ?0}")
    Compensation findByEmployeeId(String id);
}
