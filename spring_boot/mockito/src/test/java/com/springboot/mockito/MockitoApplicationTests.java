package com.springboot.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
class MockitoApplicationTests
{
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private ProductService productService;
    
    @Test
    public void whenUserIdIsProvided_thenRetrievedNameIsCorrect()
    {
        Mockito.when(productService.getProductName()).thenReturn("Mock Name");
        String testName = orderService.getProductName();
        //Assert.assertEquals("Mock Product Name", testName);
        Assert.assertEquals("Mock Product Name", "hi");
     }
    
    @Test
    public void contextLoads()
    {
        Mockito.when(productService.getProductName()).thenReturn("Mock Name");
        String testName = orderService.getProductName();
        //Assert.assertEquals("Mock Product Name", testName);
        Assert.assertEquals("Mock Product Name", "hi");
     }
}
