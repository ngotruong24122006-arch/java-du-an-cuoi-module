package org.example.presentation;

import org.example.business.impl.ProductMangerImpl;

import java.util.Scanner;

public class ProductManagerMenu {
    public static void ProductManagerMenuView(Scanner sc){
        int choose = 0;

        System.out.println("""
                =============== QUẢN LÝ SẢN PHẨM ==============
                1. Hiển thị danh sách sản phẩm
                2. Thêm sản phẩm mới
                3. Cập nhập thông tin sản phẩm
                4. Xóa sản phẩm theo ID
                5. Tìm kiếm theo Brand
                6. Tìm kiếm theo khoảng giá
                7. Tìm kiếm theo tồn kho
                8. Quay lại menu chính
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
                    new ProductMangerImpl().addProduct(sc);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    flag = false;
                    break;
                default:
                    System.out.println("Vui Lòng Nhập số 1 đến 8");
            }
        }
    }
}
