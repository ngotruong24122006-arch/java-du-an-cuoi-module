package org.example.dao.impl;

import org.example.dao.CustomerDAO;
import org.example.model.Customer;
import org.example.utils.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean existsEmail(String email) {

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("select * from Customer where email = ?");
            pstmt.setString(1,email);

            rs = pstmt.executeQuery();

            if (rs.next())
                return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(rs,pstmt,con);
        }

    }

    @Override
    public boolean addCustomer(Customer customer) {

        Connection con;
        PreparedStatement pstmt = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("insert into Customer(name,phone,email,address) values (?,?,?,?)");
            pstmt.setString(1,customer.getName());
            pstmt.setString(2,customer.getPhone());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getAddress());

            int check = pstmt.executeUpdate();

            if (check>0)
                return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(null,pstmt,con);
        }

    }

    @Override
    public Customer getCustomerById(int id) {

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("select * from Customer where id = ?");
            pstmt.setInt(1,id);

            rs = pstmt.executeQuery();
            if (rs.next())
                return new Customer(rs.getInt("id"),rs.getString("name"),rs.getString("phone"),rs.getString("email"),rs.getString("address"));
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(rs,pstmt,con);
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) {

        Connection con;
        PreparedStatement pstmt = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("update Customer set name = ?,phone = ?,email = ?,address = ? where id = ?");
            pstmt.setString(1,customer.getName());
            pstmt.setString(2,customer.getPhone());
            pstmt.setString(3,customer.getEmail());
            pstmt.setString(4, customer.getAddress());
            pstmt.setInt(5,customer.getId());

            int check = pstmt.executeUpdate();

            if (check>0)
                return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(null,pstmt,con);
        }
    }

    @Override
    public boolean deleteCustomer(int id) {
        Connection con;
        PreparedStatement pstmt = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("delete from Customer where id = ?");
            pstmt.setInt(1,id);

            int check = pstmt.executeUpdate();

            if (check>0)
                return true;
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(null,pstmt,con);
        }
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("select * from Customer");

            rs = pstmt.executeQuery();

            while (rs.next()){
                customers.add(new Customer(rs.getInt("id"),rs.getString("name"),rs.getString("phone"),rs.getString("email"),rs.getString("address")));
            }

            return customers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(rs,pstmt,con);
        }

    }
}
