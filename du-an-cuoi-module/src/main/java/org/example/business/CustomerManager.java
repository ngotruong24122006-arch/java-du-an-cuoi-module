package org.example.business;

import java.util.Scanner;

public interface CustomerManager {
    void addCustomer(Scanner sc);
    void updateCustomer(Scanner sc);
    void deleteById(Scanner sc);
    void display(Scanner sc);
}
