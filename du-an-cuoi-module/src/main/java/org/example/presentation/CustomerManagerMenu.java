package org.example.presentation;

import java.util.Scanner;

public class CustomerManagerMenu {
    public static void CustomerManagerMenuView(Scanner sc){
        int choose = 0;

        System.out.println("""
                =============== QUẢN LÝ KHÁCH HÀNG ==============
                1. Hiển thị danh sách khách hàng
                2. Thêm khách hàng mới
                3. Cập nhật Thông tin Khách hàng
                4. Xóa Khách hàng theo ID
                5. Quay lại menu chín
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
