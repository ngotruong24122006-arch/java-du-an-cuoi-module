package org.example.dao.impl;

import org.example.dao.CustomerDAO;
import org.example.model.Customer;
import org.example.utils.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
