package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.CompensationInput;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jasmine Liang
 * @email: yxl5521@rit.edu
 * @date: 2022-02-17
 */
@RestController
public class CompensationController {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;

    @PostMapping("/compensation")
    public Compensation create(@RequestBody CompensationInput compensation) {
        LOG.debug("Received compensation create request for [{}]", compensation);

        return compensationService.create(compensation);
    }

    @GetMapping("/compensation/{id}")
    public Compensation read(@PathVariable String id) {
        LOG.debug("Get compensation for id [{}]", id);
        return compensationService.read(id);
    }
}
