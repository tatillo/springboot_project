package com.springboot.h2db;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{
    @GetMapping(value = "/test")
    public String getTest()
    {
        return ("Hello Theresa, this is just a test");
    }

}
