package com.qudini.task.controller;

import com.qudini.task.service.CustomerService;
import com.qudini.task.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<Customer>> setEmployees(@Valid @RequestBody List<Customer> customers) {
        log.info("New request with size {}", customers.size());
        return ResponseEntity.ok(customerService.getCustomersSortedByDuetime(customers));
    }
}
