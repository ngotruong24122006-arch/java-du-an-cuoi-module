package org.example.dao;

import org.example.model.Invoice;

import java.util.List;

public interface InvoiceDAO {
    int addInvoice(Invoice invoice);
    List<Invoice> getAll();
    List<Invoice> findByCustomerName(String name);
    List<Invoice> findByDateDay(String date);
    List<Invoice> findByDateMonth(int month,int year);
    List<Invoice> findByDateYear(int year);

}
