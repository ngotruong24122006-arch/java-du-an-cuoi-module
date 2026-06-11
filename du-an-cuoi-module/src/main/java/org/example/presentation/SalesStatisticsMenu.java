package org.example.presentation;

import org.example.business.impl.SalesStatisticsManagerImpl;

import java.util.Scanner;

public class SalesStatisticsMenu {
    public static void SalesStatisticsMenuView(Scanner sc){
        boolean flag = true;
        while (flag){

            int choose = 0;

            System.out.println("""
                =============== QUẢN LÝ KHÁCH HÀNG ==============
                1. Doanh thu theo ngày
                2. Doanh thu theo tháng 
                3. Doanh thu theo năm
                4. Quay lại menu chín
                =========================================
                """);

            while (true){
                try {
                    System.out.println("Nhập lựa chon:");
                    choose = Integer.parseInt(sc.nextLine());
                    break;
                }catch (NumberFormatException e){
                    System.out.println("Lỗi:Vui lòng nhập 1 số nguyên");
                }
            }
            switch (choose){
                case 1:
                    new SalesStatisticsManagerImpl().getRevenueByDay(sc);
                    break;
                case 2:
                    new SalesStatisticsManagerImpl().getRevenueByMonth(sc);
                    break;
                case 3:
                    new SalesStatisticsManagerImpl().getRevenueByYear(sc);
                    break;
                case 4:
                    flag = false;
                    break;
                default:
                    System.out.println("Vui Lòng Nhập số 1 đến 4");
            }
        }
    }
}
