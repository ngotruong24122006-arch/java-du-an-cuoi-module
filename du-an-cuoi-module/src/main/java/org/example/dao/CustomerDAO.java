package org.example.dao;

import org.example.model.Customer;

import java.util.List;

public interface CustomerDAO {
    boolean existsEmail(String email);
    boolean addCustomer(Customer customer);
    Customer getCustomerById(int id);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(int id);
    List<Customer> getAll();
}
