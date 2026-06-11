package org.example.business.impl;

import org.example.business.SalesStatisticsManager;
import org.example.dao.impl.InvoiceDAOImpl;
import org.example.dao.impl.SalesStatisticsDAOImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class SalesStatisticsManagerImpl implements SalesStatisticsManager {
    @Override
    public void getRevenueByDay(Scanner sc) {
        System.out.println("Nhập ngày (yyyy-MM-dd):");
        LocalDate date = LocalDate.parse(sc.nextLine());

        BigDecimal revenue =
                new SalesStatisticsDAOImpl().getRevenueByDay(date);

        System.out.printf(
                "Doanh thu ngày %s: %,.0f VNĐ%n",
                date,
                revenue.doubleValue()
        );
    }

    @Override
    public void getRevenueByMonth(Scanner sc) {
        System.out.println("Nhập tháng:");
        int month = Integer.parseInt(sc.nextLine());

        System.out.println("Nhập năm:");
        int year = Integer.parseInt(sc.nextLine());

        BigDecimal revenue =
                new SalesStatisticsDAOImpl().getRevenueByMonth(month, year);

        System.out.printf(
                "Doanh thu tháng %d/%d: %,.0f VNĐ%n",
                month,
                year,
                revenue.doubleValue()
        );
    }

    @Override
    public void getRevenueByYear(Scanner sc) {
        System.out.println("Nhập năm:");
        int year = Integer.parseInt(sc.nextLine());

        BigDecimal revenue =
                new SalesStatisticsDAOImpl().getRevenueByYear(year);

        System.out.printf(
                "Doanh thu năm %d: %,.0f VNĐ%n",
                year,
                revenue.doubleValue()
        );
    }
}
