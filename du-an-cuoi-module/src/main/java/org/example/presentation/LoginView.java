package org.example.presentation;

import org.example.business.impl.LoginImpl;

import java.util.Scanner;

public class LoginView {

    public static void loginAdminView(Scanner sc){
        while (true){
            System.out.println("========== ĐĂNG NHẬP QUẢN TRỊ ==========");
            System.out.print("Tài Khoản: ");
            String username = sc.nextLine();
            System.out.print("Mật Khẩu : ");
            String password = sc.nextLine();
            if (!new LoginImpl().login(username,password)){
                System.out.println("Sai tài khoản hoặc mật khẩu vui lòng kiểm tra lại");
                continue;
            }
            System.out.println("========================================");
            System.out.println("Đăng nhập thành công");
            break;
        }
        MainMenu.MainMenuView(sc);
    }
}
