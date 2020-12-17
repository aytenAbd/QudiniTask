package com.qudini.task.service;

import com.qudini.task.model.Customer;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class CustomerService {

    public List<Customer> getCustomersSortedByDuetime(List<Customer> customers) {
        Collections.sort(customers, Comparator.comparing(Customer::getDuetime));
        return customers;
    }
}
