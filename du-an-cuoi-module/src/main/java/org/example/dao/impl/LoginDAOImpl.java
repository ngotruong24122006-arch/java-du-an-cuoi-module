package org.example.dao.impl;

import org.example.dao.LoginDAO;
import org.example.utils.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {

    @Override
    public boolean checkLogin(String username, String password) {

        boolean flag = false;

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("select * from Admin where username= ? and password = ?");
            pstmt.setString(1,username);
            pstmt.setString(2,password);

            rs = pstmt.executeQuery();
            flag = rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtility.closeConnection(rs,pstmt,con);
        }

        return flag;
    }
}
