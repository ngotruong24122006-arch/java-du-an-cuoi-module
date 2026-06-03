package org.example.presentation;

import java.util.Scanner;

public class SalesStatisticsMenu {
    public static void SalesStatisticsMenuView(Scanner sc){
        int choose = 0;

        System.out.println("""
                =============== QUẢN LÝ KHÁCH HÀNG ==============
                1. Doanh thu theo ngày
                2. Doanh thu theo tháng 
                3. Doanh thu theo năm
                4. Quay lại menu chín
                =========================================
                """);

        boolean flag = true;
        while (flag){
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
                    break;
                case 2:
                    break;
                case 3:
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
