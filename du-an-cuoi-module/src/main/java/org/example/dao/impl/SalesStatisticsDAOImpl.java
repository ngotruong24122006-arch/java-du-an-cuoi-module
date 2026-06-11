package org.example.dao.impl;

import org.example.dao.SalesStatisticsDAO;
import org.example.utils.DBUtility;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SalesStatisticsDAOImpl implements SalesStatisticsDAO {
    @Override
    public BigDecimal getRevenueByDay(LocalDate date) {

        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("""
                SELECT COALESCE(SUM(total_amount),0) revenue
                FROM Invoice
                WHERE DATE(created_at) = ?
                """);
            pstmt.setDate(1,java.sql.Date.valueOf(date));

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal("revenue");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(rs,pstmt,con);
        }

        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getRevenueByMonth(int month, int year) {
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("""
                SELECT COALESCE(SUM(total_amount),0) revenue
                FROM Invoice
                WHERE EXTRACT(MONTH FROM created_at) = ?
                AND EXTRACT(YEAR FROM created_at) = ?
                """);
            pstmt.setInt(1,month);
            pstmt.setInt(2,year);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal("revenue");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(rs,pstmt,con);
        }

        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getRevenueByYear(int year) {
        Connection con;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        con = DBUtility.openConnection();

        try {
            pstmt = con.prepareStatement("""
                SELECT COALESCE(SUM(total_amount),0) revenue
                FROM Invoice
                WHERE EXTRACT(YEAR FROM created_at) = ?
                """);
            pstmt.setInt(1,year);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getBigDecimal("revenue");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtility.closeConnection(rs,pstmt,con);
        }

        return BigDecimal.ZERO;
    }
}
