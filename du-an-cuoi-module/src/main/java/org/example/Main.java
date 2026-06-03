package org.example;

import org.example.presentation.LoginView;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println("""
                ========== HỆ THÔNG QUẢN LÝ CỬA HÀNG ==========
                1. Đăng Nhập Admin
                2.Thoát
                ===============================================
                """);
            int choose;
            while (true) {
                try {
                    System.out.println("Nhập lựa chọn:");
                    choose = Integer.parseInt(sc.nextLine());
                    break;
                }catch (NumberFormatException e){
                    System.out.println("Lỗi: Vui Lòng Nhập 1 số");
                }
            }
            switch (choose){
                case 1:
                    LoginView.loginAdminView(sc);
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.println("Vui Lòng Nhập số 1 hoặc 2");
            }
        }
    }
}