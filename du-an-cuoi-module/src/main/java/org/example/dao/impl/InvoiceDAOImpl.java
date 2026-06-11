package org.example.dao.impl;

import org.example.dao.InvoiceDAO;
import org.example.model.Invoice;
import org.example.utils.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAOImpl implements InvoiceDAO {
    @Override
    public int addInvoice(Invoice invoice) {

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("insert into Invoice(customer_id,total_amount) values (?,?) returning id");
            pstmt.setInt(1,invoice.getCustomerId());
            pstmt.setBigDecimal(2,invoice.getTotalAmount());

            rs = pstmt.executeQuery();

            if (rs.next()){
                return rs.getInt("id");
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(rs,pstmt,con);
        }

    }

    @Override
    public List<Invoice> getAll() {
        List<Invoice> invoices = new ArrayList<>();

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("select * from Invoice");

            rs = pstmt.executeQuery();

            while (rs.next()){
                invoices.add(new Invoice(rs.getInt("id"),rs.getInt("customer_id"),rs.getTimestamp("created_at").toLocalDateTime(),rs.getBigDecimal("total_amount")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(rs,pstmt,con);
        }

        return invoices;
    }

    @Override
    public List<Invoice> findByCustomerName(String name) {
        List<Invoice> invoices = new ArrayList<>();

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("""
                    select i.id,
                               i.customer_id, 
                               c.name,
                               i.created_at,
                               i.total_amount
                        from Invoice i
                        join Customer c
                        on i.customer_id = c.id
                        where c.name ilike ?
                        order by i.id
                    """);
            pstmt.setString(1,"%"+name+"%");

            rs = pstmt.executeQuery();

            while (rs.next()){
                invoices.add(new Invoice(rs.getInt("id"),rs.getInt("customer_id"),rs.getTimestamp("created_at").toLocalDateTime(),rs.getBigDecimal("total_amount")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(rs,pstmt,con);
        }

        return invoices;
    }

    @Override
    public List<Invoice> findByDateDay(String date) {
        List<Invoice> invoices = new ArrayList<>();

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("""
                    SELECT *
                                     FROM Invoice
                                     WHERE DATE(created_at) = ?;
                    """);
            pstmt.setDate(1,java.sql.Date.valueOf(date));

            rs = pstmt.executeQuery();

            while (rs.next()){
                invoices.add(new Invoice(rs.getInt("id"),rs.getInt("customer_id"),rs.getTimestamp("created_at").toLocalDateTime(),rs.getBigDecimal("total_amount")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(rs,pstmt,con);
        }

        return invoices;
    }

    @Override
    public List<Invoice> findByDateMonth(int month, int year) {
        List<Invoice> invoices = new ArrayList<>();

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("""
                    SELECT *
                                     FROM Invoice
                                     WHERE EXTRACT(MONTH FROM created_at) = ?
                                     AND EXTRACT(YEAR FROM created_at) = ?;
                    """);
            pstmt.setInt(1,month);
            pstmt.setInt(2,year);

            rs = pstmt.executeQuery();

            while (rs.next()){
                invoices.add(new Invoice(rs.getInt("id"),rs.getInt("customer_id"),rs.getTimestamp("created_at").toLocalDateTime(),rs.getBigDecimal("total_amount")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(rs,pstmt,con);
        }

        return invoices;
    }

    @Override
    public List<Invoice> findByDateYear(int year) {
        List<Invoice> invoices = new ArrayList<>();

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("""
                    SELECT *
                    FROM Invoice
                    WHERE EXTRACT(YEAR FROM created_at) = ?;
                    """);
            pstmt.setInt(1,year);

            rs = pstmt.executeQuery();

            while (rs.next()){
                invoices.add(new Invoice(rs.getInt("id"),rs.getInt("customer_id"),rs.getTimestamp("created_at").toLocalDateTime(),rs.getBigDecimal("total_amount")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(rs,pstmt,con);
        }

        return invoices;
    }
}
