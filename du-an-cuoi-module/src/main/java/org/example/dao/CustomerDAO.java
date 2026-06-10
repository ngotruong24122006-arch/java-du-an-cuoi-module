package org.example.dao;

import org.example.model.Customer;

public interface CustomerDAO {
    boolean existsEmail(String email);
    boolean addCustomer(Customer customer);
}
