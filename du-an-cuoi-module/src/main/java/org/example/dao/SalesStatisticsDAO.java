package org.example.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface SalesStatisticsDAO {
    BigDecimal getRevenueByDay(LocalDate  date);
    BigDecimal getRevenueByMonth(int month, int year);
    BigDecimal getRevenueByYear(int year);
}
