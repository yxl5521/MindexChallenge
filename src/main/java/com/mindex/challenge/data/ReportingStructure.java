package com.mindex.challenge.data;
/**
 * @author Jasmine Liang
 * @email: yxl5521@rit.edu
 * @date: 2022-02-17
 */

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure() {
    }

}
