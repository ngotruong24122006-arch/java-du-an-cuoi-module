package org.example.presentation;

import java.util.Scanner;

public class MainMenu {
    public static void MainMenuView(Scanner sc){


        boolean flag = true;
        while (flag){
            int choose = 0;

            System.out.println("""
                =============== MENU CHÍNH ==============
                1. Quản lý sản phẩm điện thoại
                2. QUản lý khách hàng
                3. Quản lý hóa đơn
                4. Thống kê doanh thu
                5. Đăng xuất 
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
                    ProductManagerMenu.ProductManagerMenuView(sc);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("Vui Lòng Nhập số 1 đến 5");
            }
        }
    }
}
