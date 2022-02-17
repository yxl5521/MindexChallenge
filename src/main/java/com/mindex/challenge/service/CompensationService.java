package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationInput;

/**
 * @author Jasmine Liang
 * @email: yxl5521@rit.edu
 * @date: 2022-02-17
 */
public interface CompensationService {
    Compensation create(CompensationInput compensation);
    Compensation read(String id);
}
