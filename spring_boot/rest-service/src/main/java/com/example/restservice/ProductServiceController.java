package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.model.Product;
import com.example.restservice.model.ProductDeleteException;
import com.example.restservice.model.ProductNotFoundException;
import com.example.restservice.model.ProductServiceInterface;

@RestController
public class ProductServiceController
{
    @Autowired
    ProductServiceInterface prodServInterface;
    
    @RequestMapping("/products")
    @CrossOrigin(origins = "http://localhost:9090")
    public ResponseEntity<Object> getProducts()
    {
        return new ResponseEntity(prodServInterface.getProducts(), HttpStatus.OK);
    }
    
    @GetMapping(value="/view/{id}")
    //or @RequestMapping("value="view/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> viewProduct(@PathVariable("id") String id)
    {
        return new ResponseEntity(prodServInterface.viewProduct(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product)
    {
        prodServInterface.createProduct(product);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/update/{id}", 
            method = {RequestMethod.PUT, RequestMethod.GET})
    public ResponseEntity<Object> updateProduct(
            @PathVariable("id") String id, 
            @RequestBody Product product) throws ProductNotFoundException
    {
        prodServInterface.updateProduct(id, product);
        return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public ResponseEntity<Object> delete(@PathVariable("id") String id)
            throws ProductDeleteException
    {
        prodServInterface.deleteProduct(id);
        return new ResponseEntity<>("Product id: " + id + " has been deleted", HttpStatus.OK);
    }
    
}
