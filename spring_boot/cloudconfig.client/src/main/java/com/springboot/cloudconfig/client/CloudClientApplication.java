package com.springboot.cloudconfig.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RefreshScope
@RestController
public class CloudClientApplication
{
    @Value("${welcome.message}")
    private String welcomeText;
    
    @Value("${name}")
    private String name;
    
    public static void main(String[] args)
    {
        SpringApplication.run(CloudClientApplication.class, args);
    }

    @RequestMapping(value = "/")
    public String welcomeText()
    {
        return welcomeText;
    }
    
    @RequestMapping(value = "/name")
    public String getName()
    {
        return name;
    }

}
