package org.example.dao.impl;

import org.example.dao.ProductDAO;
import org.example.model.Product;
import org.example.utils.DBUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public Product getProductById(int id) {
        Product product = new Product();

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("select * from Product where id=?");
            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();
            if (rs.next()){
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setBrand(rs.getString("brand"));
                product.setPrice(rs.getBigDecimal("price"));
                product.setStock(rs.getInt("stock"));
                
                return product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(rs,pstmt,con);
        }

        return null;
    }

    @Override
    public boolean updateProduct(Product product) {

        Connection con;
        PreparedStatement pstmt = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("update Product set name = ?,brand = ?,price = ?,stock = ? where id = ?");
            pstmt.setString(1,product.getName());
            pstmt.setString(2, product.getBrand());
            pstmt.setBigDecimal(3,product.getPrice());
            pstmt.setInt(4,product.getStock());
            pstmt.setInt(5,product.getId());

            return pstmt.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtility.closeConnection(null,pstmt,con);
        }
    }

    @Override
    public boolean deleteProductById(int id) {

        Connection con;
        PreparedStatement pstmt = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("delete from Product where id = ?");
            pstmt.setInt(1,id);

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        Connection con;
        Statement stmt = null;
        ResultSet rs = null;

        con = DBUtility.openConnection();

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from Product order by id asc");

            while (rs.next()){
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBUtility.closeConnection(rs,stmt,con);
        }

        return products;
    }

    @Override
    public List<Product> getAllByBrand(String brand) {
        List<Product> products = new ArrayList<>();

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("select * from Product where brand ilike ? order by id asc");
            pstmt.setString(1,"%"+brand+"%");
            rs = pstmt.executeQuery();

            while (rs.next()){
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getBigDecimal("price"),
                        rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(rs,pstmt,con);
        }

        return products;
    }

}
