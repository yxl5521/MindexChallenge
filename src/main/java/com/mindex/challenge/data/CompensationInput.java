package com.mindex.challenge.data;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * @author Jasmine Liang
 * @email: yxl5521@rit.edu
 * @date: 2022-02-17
 */
@Getter
@Setter
public class CompensationInput {
    private String employeeId;
    private String effectiveDate;
    private String salary;
}
