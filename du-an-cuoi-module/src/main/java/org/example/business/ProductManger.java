package org.example.business;

import java.util.Scanner;

public interface ProductManger {
    void addProduct(Scanner sc);
    void updateProduct(Scanner sc);
    void deleteProduct(Scanner sc);
    void display();
    void searchByBrand(Scanner sc);
    void searchByPrice(Scanner sc);
    void searchByName(Scanner sc);
}
