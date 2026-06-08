package org.example.presentation;

import java.util.Scanner;

public class InvoiceManagerMenu {
    public static void InvoiceManagerMenuView(Scanner sc){


        boolean flag = true;
        while (flag){
            int choose = 0;

            System.out.println("""
                =============== QUẢN LÝ HÓA ĐƠN ==============
                1. Hiển thị danh sách hóa đơn
                2. Thêm mới hóa đơn
                3. Tìm kiếm hóa đơn 
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
