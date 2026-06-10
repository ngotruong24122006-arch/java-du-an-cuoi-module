package org.example.business.impl;

import org.example.business.CustomerManager;
import org.example.dao.impl.CustomerDAOImpl;
import org.example.model.Customer;

import java.util.Scanner;

public class CustomerManagerImpl implements CustomerManager {
    @Override
    public void addCustomer(Scanner sc) {
        Customer customer = new Customer();

        while (true){
            System.out.println("Nhập tên Khach hang:");
            String name = sc.nextLine();

            if (!name.trim().isEmpty()){
                customer.setName(name);
                break;
            }
            System.out.println("Tên Khách hàng không được để chống");
        }

        while (true){
            System.out.println("Nhập số điện thoại Khach hang:");
            String phone = sc.nextLine();

            if (!phone.matches("^0\\d{9}$")){
                customer.setPhone(phone);
                break;
            }
            System.out.println("Số điện thoại không phù hợp");
        }

        while (true){
            System.out.println("Nhập email Khach hang:");
            String email = sc.nextLine();

            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") && !new CustomerDAOImpl().existsEmail(email)){
                customer.setEmail(email);
                break;
            } else if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                System.out.println("Email không hợp lệ");
            } else if (new CustomerDAOImpl().existsEmail(email)) {
                System.out.println("Email đã tồn tại");
            }
        }

        System.out.println("Nhập địa chỉ:");
        customer.setAddress(sc.nextLine());

        if (new CustomerDAOImpl().addCustomer(customer)){
            System.out.println("Thêm khách hàn mới thành công");
        }else {
            System.out.println("Thêm khách hàn mới thất bại");
        }
    }
}
