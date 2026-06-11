package org.example.business;

import java.util.Scanner;

public interface InvoiceManger {
    void addInvoice(Scanner sc);
    void display(Scanner sc);
    void searchByCustomerName(Scanner sc);
    void searchByDate(Scanner sc);
}
