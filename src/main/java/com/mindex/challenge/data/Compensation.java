package com.mindex.challenge.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Jasmine Liang
 * @email: yxl5521@rit.edu
 * @date: 2022-02-17
 */
@Setter
@Getter
public class Compensation {
    private Employee employee;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;
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
