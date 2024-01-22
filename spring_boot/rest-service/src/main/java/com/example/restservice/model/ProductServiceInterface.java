package com.example.restservice.model;

import java.util.Collection;

public interface ProductServiceInterface
{
    public abstract void createProduct(Product product);
    
    public abstract void updateProduct(String id, Product product) throws ProductNotFoundException;
    
    public abstract void deleteProduct(String id) throws ProductNotFoundException;
    
    public abstract Collection<Product> getProducts();
    
    public abstract Product viewProduct(String id);
    
}
