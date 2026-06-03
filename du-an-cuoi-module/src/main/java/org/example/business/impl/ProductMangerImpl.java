package org.example.business.impl;

import org.example.business.ProductManger;
import org.example.dao.impl.ProductDAOImpl;
import org.example.model.Product;

import java.math.BigDecimal;
import java.util.Scanner;

public class ProductMangerImpl implements ProductManger {
    @Override
    public void addProduct(Scanner sc) {
        Product product = new Product();

        while (true){
            System.out.println("Nhập tên sản phẩm:");
            String name = sc.nextLine();

            if (!name.trim().isEmpty()){
                product.setName(name);
                break;
            }
            System.out.println("Tên sản phẩm không được để chống");
        }

        while (true){
            System.out.println("Nhập tên hãng sản xuất:");
            String bName = sc.nextLine();

            if (!bName.trim().isEmpty()){
                product.setBrand(bName);
                break;
            }
            System.out.println("Tên hãng sản xuất không được để chống");
        }

        while (true){
            System.out.println("Nhập giá của sản phẩm:");

            try {
                double price = Double.parseDouble(sc.nextLine());

                if (price>0){
                    product.setPrice(BigDecimal.valueOf(price));
                    break;
                }

                System.out.println("Giá phải lớn hơn 0");
            } catch (Exception e) {
                System.out.println("Lỗi:phải là số");
            }

        }

        while (true){
            System.out.println("Nhập tồn kho của sản phẩm:");

            try {
                int stock = Integer.parseInt(sc.nextLine());

                if (stock >=0){
                    product.setStock(stock);
                    break;
                }

                System.out.println("Tồn kho phải >= 0");
            } catch (Exception e) {
                System.out.println("Lỗi:phải là số");
            }

        }

        if (new ProductDAOImpl().insertProduct(product)){
            System.out.println("Thêm sản phảm thành công");
        }
    }
}
