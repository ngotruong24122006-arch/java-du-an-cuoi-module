package org.example.dao.impl;

import org.example.dao.InvoiceDetailDAO;
import org.example.model.InvoiceDetail;
import org.example.utils.DBUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InvoiceDetailDAOImpl implements InvoiceDetailDAO {
    @Override
    public boolean addInvoiceDetail(InvoiceDetail detail) {

        Connection con;
        PreparedStatement pstmt = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("insert into Invoice_Details(invoice_id,product_id,quantity,unit_price) values(?,?,?,?)");
            pstmt.setInt(1,detail.getInvoiceId());
            pstmt.setInt(2,detail.getProductId());
            pstmt.setInt(3,detail.getQuantity());
            pstmt.setBigDecimal(4,detail.getUnitPrice());

            int check = pstmt.executeUpdate();
            return check >0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
