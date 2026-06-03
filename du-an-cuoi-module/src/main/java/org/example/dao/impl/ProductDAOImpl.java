package org.example.dao.impl;

import org.example.dao.ProductDAO;
import org.example.model.Product;
import org.example.utils.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public boolean insertProduct(Product product) {

        Connection con;
        PreparedStatement pstmt = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("insert into Product(name,brand,price,stock) values (?,?,?,?)");

            pstmt.setString(1,product.getName());
            pstmt.setString(2, product.getBrand());
            pstmt.setBigDecimal(3,product.getPrice());
            pstmt.setInt(4,product.getStock());

            int check = pstmt.executeUpdate();

            if (check > 0)
                return true;
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtility.closeConnection(null,pstmt,con);
        }

    }
}
