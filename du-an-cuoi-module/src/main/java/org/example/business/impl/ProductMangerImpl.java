package org.example.business.impl;

import org.example.business.ProductManger;
import org.example.dao.impl.ProductDAOImpl;
import org.example.model.Product;

import java.math.BigDecimal;
import java.util.List;
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

        if (new ProductDAOImpl() {
            @Override
            public List<Product> getAll() {
                return List.of();
            }
        }.insertProduct(product)){
            System.out.println("Thêm sản phảm thành công");
        }else
            System.out.println("Thêm sản phẩm thất bại ");
    }

    @Override
    public void updateProduct(Scanner sc) {
        Product product = null;

        while (true){
            System.out.println("Nhập id sản phẩm muốn sửa:");
            int id = Integer.parseInt(sc.nextLine());

            product = new ProductDAOImpl().getProductById(id);

            if (product != null){
                break;
            }

            System.out.println("Id không tồn tại,vui lòng nhập lại");
        }

        System.out.println("Thông tin hiện tại: "+product.toString());

        while (true){
            System.out.println("Nhập tên sản phẩm mới:");
            String name = sc.nextLine();

            if (!name.trim().isEmpty()){
                product.setName(name);
                break;
            }
            System.out.println("Tên sản phẩm không được để chống");
        }

        while (true){
            System.out.println("Nhập tên hãng sản xuất mới:");
            String bName = sc.nextLine();

            if (!bName.trim().isEmpty()){
                product.setBrand(bName);
                break;
            }
            System.out.println("Tên hãng sản xuất không được để chống");
        }

        while (true){
            System.out.println("Nhập giá của sản phẩm mới:");

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
            System.out.println("Nhập tồn kho của sản phẩm mới:");

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

        if (new ProductDAOImpl().updateProduct(product))
            System.out.println("Sửa sản phẩm thành công");
        else
            System.out.println("Sửa sản phẩm thất bại");

    }

    @Override
    public void deleteProduct(Scanner sc) {

        Product product = null;

        while (true){
            System.out.println("Nhập id sản phẩm muốn sửa:");
            int id = Integer.parseInt(sc.nextLine());

            product = new ProductDAOImpl().getProductById(id);

            if (product != null){
                break;
            }

            System.out.println("Id không tồn tại,vui lòng nhập lại");
        }

        System.out.println("Bạn có muốn xóa sản phẩm này không?(Y/N)");
        String check = sc.nextLine();

        if (check.equals("Y")){
            if (new ProductDAOImpl().deleteProductById(product.getId()))
                System.out.println("Xóa sản phẩm thành công ");
            else
                System.out.println("Xóa sản phẩm thất bại ");
        }else {
            System.out.println("Xóa sản phẩm thất bại ");
        }

    }

    @Override
    public void display() {
        List<Product> products = new ProductDAOImpl().getAll();

        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm trống!");
            return;
        }

        System.out.printf("%-5s %-25s %-15s %-15s %-10s%n",
                "ID", "Tên sản phẩm", "Brand", "Giá", "Tồn kho");

        System.out.println("--------------------------------------------------------------------------");
        for (Product p : products){
            System.out.println(p);
        }
    }

    @Override
    public void searchByBrand(Scanner sc) {
        System.out.println("Nhập brand muốn tìm:");
        String brand = sc.nextLine();

        List<Product> products = new ProductDAOImpl().getAllByBrand(brand);

        if (products.isEmpty())
            System.out.println("brand này chưa có sản phẩm nào ");

        System.out.printf("%-5s %-25s %-15s %-15s %-10s%n",
                "ID", "Tên sản phẩm", "Brand", "Giá", "Tồn kho");

        System.out.println("--------------------------------------------------------------------------");
        for (Product p : products){
            System.out.println(p);
        }
    }
}
