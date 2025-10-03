package com.example.service;


import com.example.model.Product;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> getAllProducts() {
        return entityManager.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }

    public Product findProductById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public Product createProduct(Product product) {
        entityManager.persist(product);
        return product;
    }

    public Product updateProduct(Product product) {
        return entityManager.merge(product);
    }

    public void deleteProduct(Long id) {
        Product product = entityManager.find(Product.class, id);
        if (product != null) {
            entityManager.remove(product);
        }
    }
}