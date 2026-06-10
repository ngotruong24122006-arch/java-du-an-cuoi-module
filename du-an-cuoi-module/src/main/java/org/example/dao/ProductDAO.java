package org.example.dao;

import org.example.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDAO {
    boolean insertProduct(Product product);
    Product getProductById(int id);
    boolean updateProduct(Product product);
    boolean deleteProductById(int id);
    List<Product> getAll();
    List<Product> getAllByBrand(String brand);
    List<Product> getAllByPrice(BigDecimal priceMin,BigDecimal priceMax);
    List<Product> getAllByName(String name);
}
