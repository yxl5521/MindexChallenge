package com.mindex.challenge.data;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * @author Jasmine Liang
 * @email: yxl5521@rit.edu
 * @date: 2022-02-17
 */
@Setter
@Getter
public class Compensation {
    private Employee employee;
    private ZonedDateTime effectiveDate;
    private String salary;

    @Override
    public String toString() {
        return "{" +
                " employee: " + getEmployee()+
                ", salary: " + getSalary() +
                ", effectiveDate: " + getEffectiveDate() +
                "}";
    }
}
