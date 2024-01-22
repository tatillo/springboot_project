package com.example.restservice.application;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApplicationRunnerExample implements ApplicationRunner
{
    public static void main(String[] args)
    {
        SpringApplication.run(ApplicationRunnerExample.class, args);
    }

    @Override
    public void run(ApplicationArguments arg0) throws Exception
    {
        System.out.println("Hello World from Application Runner");
    }
    
    @GetMapping(value="/apprunner/hello")
    public String hello()
    {
        return ("Hello from TomCat Application Runner Example");
    }
}
