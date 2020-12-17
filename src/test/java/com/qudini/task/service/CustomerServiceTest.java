package com.qudini.task.service;

import com.qudini.task.model.Customer;
import org.junit.Before;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerServiceTest {
    CustomerService customerService;

    @Before
    public void init() {
        customerService = new CustomerService();
    }

    @Test
    public void testCustomersSortedByDue() {
        //prep
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXXXX");
        ZonedDateTime duetime1 = ZonedDateTime.parse("2013-12-28T14:11:12-08:00", formatter);
        ZonedDateTime duetime2 = ZonedDateTime.parse("2010-12-28T14:11:12-08:00", formatter);
        Customer customer1 = new Customer(123, "Ivan", duetime1, duetime1);
        Customer customer2 = new Customer(125, "Anna", duetime2, duetime2);
        List<Customer> customers = Arrays.asList(customer1, customer2);
        List<Customer> expected = Arrays.asList(customer2, customer1);

        //call
        List<Customer> actual = customerService.getCustomersSortedByDuetime(customers);

        //validate
        assertEquals(expected, actual);
    }
}