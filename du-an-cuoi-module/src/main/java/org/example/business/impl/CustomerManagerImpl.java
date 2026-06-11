package org.example.business.impl;

import org.example.business.CustomerManager;
import org.example.dao.impl.CustomerDAOImpl;
import org.example.dao.impl.ProductDAOImpl;
import org.example.model.Customer;

import java.util.List;
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

            if (phone.matches("^0\\d{9}$")){
                customer.setPhone(phone);
                break;
            }
            System.out.println("Số điện thoại không phù hợp");
        }

        while (true){
            System.out.println("Nhập email Khach hang:");
            String email = sc.nextLine();

            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") && !new CustomerDAOImpl().existsEmail(email)){
                customer.setEmail(email);
                break;
            } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
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

    @Override
    public void updateCustomer(Scanner sc) {
        Customer customerOld = null;

        while (true){
            System.out.println("Nhập id Khách hàng muốn sửa:");
            int id = Integer.parseInt(sc.nextLine());

            customerOld = new CustomerDAOImpl().getCustomerById(id);

            if (customerOld != null){
                break;
            }

            System.out.println("Id không tồn tại,vui lòng nhập lại");
        }

        System.out.println("Thông tin hiện tại của khách hàng: \n"+customerOld.toString());

        Customer customerNew = new Customer();

        System.out.println("Vui lòng nhập thông tin mới:");

        while (true){
            System.out.println("Nhập tên Khach hang:");
            String name = sc.nextLine();

            if (!name.trim().isEmpty()){
                customerNew.setName(name);
                break;
            }
            System.out.println("Tên Khách hàng không được để chống");
        }

        while (true){
            System.out.println("Nhập số điện thoại Khach hang:");
            String phone = sc.nextLine();

            if (phone.matches("^0\\d{9}$")){
                customerNew.setPhone(phone);
                break;
            }
            System.out.println("Số điện thoại không phù hợp");
        }

        while (true){
            System.out.println("Nhập email Khach hang:");
            String email = sc.nextLine();

            if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") && !new CustomerDAOImpl().existsEmail(email)){
                customerNew.setEmail(email);
                break;
            } else if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                System.out.println("Email không hợp lệ");
            } else if (new CustomerDAOImpl().existsEmail(email)) {
                System.out.println("Email đã tồn tại");
            }
        }

        System.out.println("Nhập địa chỉ:");
        customerNew.setAddress(sc.nextLine());

        if (new CustomerDAOImpl().updateCustomer(customerNew))
            System.out.println("Cập nhật thành công");
        else
            System.out.println("Cập nhật thất bại");
    }

    @Override
    public void deleteById(Scanner sc) {
        Customer customer = null;

        while (true){
            System.out.println("Nhập id Khách hàng muốn xóa:");
            int id = Integer.parseInt(sc.nextLine());

            customer = new CustomerDAOImpl().getCustomerById(id);

            if (customer != null){
                break;
            }

            System.out.println("Id không tồn tại,vui lòng nhập lại");
        }

        System.out.println("Bạn có muốn xóa sản phẩm này không?(Y/N)");
        String check = sc.nextLine();

        if (check.equals("Y")){
            if (new CustomerDAOImpl().deleteCustomer(customer.getId()))
                System.out.println("Xóa sản phẩm thành công ");
            else
                System.out.println("Xóa sản phẩm thất bại ");
        }else {
            System.out.println("Xóa sản phẩm thất bại ");
        }
    }

    @Override
    public void display(Scanner sc) {
        List<Customer> customers = new CustomerDAOImpl().getAll();

        if (customers.isEmpty()){
            System.out.println("Không có sản phẩm nào");
            return;
        }


        System.out.printf("%-5s %-20s %-15s %-25s %-30s\n",
                "id", "name", "phone", "email", "address");
        System.out.println("-------------------------------------------------------------------------------");

        for (Customer customer : customers){
            System.out.println(customer);
        }
    }


}
