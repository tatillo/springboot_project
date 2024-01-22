package com.example.restservice.businesslogic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.restservice.model.Product;
import com.example.restservice.model.ProductDeleteException;
import com.example.restservice.model.ProductNotFoundException;
import com.example.restservice.model.ProductServiceInterface;

@Service
public class ProductServiceImpl implements ProductServiceInterface
{
    private static Map<String, Product> productRepo = new HashMap<String, Product>();

    static
    {
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);

        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }

    @Override
    public void createProduct(Product product)
    {
        productRepo.put(product.getId(), product);        
    }

    @Override
    public void updateProduct(String id, Product product) throws ProductNotFoundException
    {
        if(!productRepo.containsKey(id))
        {
            throw new ProductNotFoundException();
        }
        productRepo.remove(id);
        product.setId(id);
        productRepo.put(id, product);        
    }

    @Override
    public void deleteProduct(String id) throws ProductNotFoundException
    {
        if(!productRepo.containsKey(id))
        {
            throw new ProductDeleteException();
        }
        
        productRepo.remove(id);
    }

    @Override
    public Collection<Product> getProducts()
    {
        return productRepo.values();
    }
    
    public Product viewProduct(String id)
    {
        return productRepo.get(id);
    }

}
