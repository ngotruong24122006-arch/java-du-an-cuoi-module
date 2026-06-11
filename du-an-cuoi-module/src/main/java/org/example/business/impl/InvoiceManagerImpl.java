package org.example.business.impl;

import org.example.business.InvoiceManger;
import org.example.dao.impl.CustomerDAOImpl;
import org.example.dao.impl.InvoiceDAOImpl;
import org.example.dao.impl.InvoiceDetailDAOImpl;
import org.example.dao.impl.ProductDAOImpl;
import org.example.model.Customer;
import org.example.model.Invoice;
import org.example.model.InvoiceDetail;
import org.example.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InvoiceManagerImpl implements InvoiceManger {
    @Override
    public void addInvoice(Scanner sc) {
        Invoice invoice = new Invoice();
        List<InvoiceDetail> invoiceDetails = new ArrayList<>();

        while (true){
            System.out.println("Nhập id Khách hàng:");
            int id = Integer.parseInt(sc.nextLine());

            Customer customer = new CustomerDAOImpl().getCustomerById(id);

            if (customer != null){
                invoice.setCustomerId(id);
                break;
            }

            System.out.println("Id không tồn tại,vui lòng nhập lại");
        }

        boolean flag = true;

        while (flag){
            Product product;
            while (true){
                System.out.println("Nhập id sản phẩm:(0=exit)");
                int id = Integer.parseInt(sc.nextLine());

                if (id == 0){
                    flag = false;
                    break;
                }

                product = new ProductDAOImpl().getProductById(id);

                if (product != null){
                    InvoiceDetail invoiceDetail = invoiceDetails.stream().filter(d -> d.getProductId() == id).findFirst().orElse(null);
                    if (invoiceDetail == null){
                        InvoiceDetail detailNew = new InvoiceDetail();
                        detailNew.setProductId(id);
                        detailNew.setUnitPrice(product.getPrice());
                        while (true){
                            System.out.println("Nhập số lượng");
                            int quantity = Integer.parseInt(sc.nextLine());
                            if (quantity <= product.getStock()){
                                detailNew.setQuantity(quantity);
                                break;
                            }
                            System.out.println("số lượng không phù hợp");
                        }

                        invoiceDetails.add(detailNew);
                        break;
                    }
                    while (true){
                        System.out.println("Nhập số lượng");
                        int quantity = Integer.parseInt(sc.nextLine());
                        if (invoiceDetail.getQuantity()+quantity<= product.getStock()){
                            invoiceDetail.setQuantity(invoiceDetail.getQuantity()+quantity);
                            break;
                        }
                    }
                    break;
                }

                System.out.println("Id không tồn tại,vui lòng nhập lại");
            }

        }

        BigDecimal sum = BigDecimal.ZERO;
        for (InvoiceDetail d : invoiceDetails){
            sum.add(
                    d.getUnitPrice().multiply(
                            BigDecimal.valueOf(d.getQuantity())
                    )
            );
        }

        invoice.setTotalAmount(sum);

        int invoiceId = new InvoiceDAOImpl().addInvoice(invoice);

        if (invoiceId == -1){
            System.out.println("thêm hóa đơn thất bại ");
            return;
        }

        if (invoiceDetails.isEmpty())
            return;

        for (InvoiceDetail detail : invoiceDetails){
            detail.setInvoiceId(invoiceId);
            if (new InvoiceDetailDAOImpl().addInvoiceDetail(detail));
        }

    }

    @Override
    public void display(Scanner sc) {
        List<Invoice> invoices = new InvoiceDAOImpl().getAll();

        if (invoices.isEmpty()){
            System.out.println("Không có hóa đơn nào");
            return;
        }

        System.out.printf("%-5s %-10s %-25s %-15s",
                "id", "customerId", "createdAt", "totalAmount");
        System.out.println();

        System.out.println("-----------------------------------------------");

        for (Invoice i : invoices)
            System.out.println(i);
    }

    @Override
    public void searchByCustomerName(Scanner sc) {
        System.out.println("Nhập tên khách hàng");
        String name = sc.nextLine();

        List<Invoice> invoices = new InvoiceDAOImpl().findByCustomerName(name);

        if (invoices.isEmpty()){
            System.out.println("Không có hóa đơn nào");
            return;
        }

        System.out.printf("%-5s %-10s %-25s %-15s",
                "id", "customerId", "createdAt", "totalAmount");
        System.out.println();

        System.out.println("-----------------------------------------------");

        for (Invoice i : invoices)
            System.out.println(i);
    }

    @Override
    public void searchByDate(Scanner sc) {
        System.out.println("""
                1.Theo ngày
                2.Theo tháng 
                3.Theo năm
                """);
        int choose;
        while (true){
            try {
                System.out.println("Nhập lựa chon:");
                choose = Integer.parseInt(sc.nextLine());
                break;
            }catch (NumberFormatException e){
                System.out.println("Lỗi:Vui lòng nhập 1 số nguyên");
            }
        }
        switch (choose) {
            case 1:
                System.out.println("Nhập ngày(YYYY-MM-DD)");
                String date = sc.nextLine();
                List<Invoice> invoices = new InvoiceDAOImpl().findByDateDay(date);

                if (invoices.isEmpty()){
                    System.out.println("Không có hóa đơn nào");
                    return;
                }

                System.out.printf("%-5s %-10s %-25s %-15s",
                        "id", "customerId", "createdAt", "totalAmount");
                System.out.println();

                System.out.println("-----------------------------------------------");

                for (Invoice i : invoices)
                    System.out.println(i);
                break;
            case 2:
                System.out.println("Nhập tháng");
                int month = Integer.parseInt(sc.nextLine());
                System.out.println("Nhập năm");
                int year = Integer.parseInt(sc.nextLine());
                List<Invoice> invoices1 = new InvoiceDAOImpl().findByDateMonth(month,year);

                if (invoices1.isEmpty()){
                    System.out.println("Không có hóa đơn nào");
                    return;
                }

                System.out.printf("%-5s %-10s %-25s %-15s",
                        "id", "customerId", "createdAt", "totalAmount");
                System.out.println();

                System.out.println("-----------------------------------------------");

                for (Invoice i : invoices1)
                    System.out.println(i);
                break;
            case 3:
                System.out.println("Nhập năm");
                int year1 = Integer.parseInt(sc.nextLine());
                List<Invoice> invoices2 = new InvoiceDAOImpl().findByDateYear(year1);

                if (invoices2.isEmpty()){
                    System.out.println("Không có hóa đơn nào");
                    return;
                }

                System.out.printf("%-5s %-10s %-25s %-15s",
                        "id", "customerId", "createdAt", "totalAmount");
                System.out.println();

                System.out.println("-----------------------------------------------");

                for (Invoice i : invoices2)
                    System.out.println(i);
                break;
            default:
                System.out.println("Vui Lòng Nhập số 1 đến 3");
        }

    }
}
