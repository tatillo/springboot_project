package com.springboot.h2db;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*@SpringBootApplication(scanBasePackages = 
{ "com.springboot.h2db", "com.springboot.controllers"} )*/

//@EnableJpaRepositories(basePackages= {"com.springboot.repositories.*"})
@EnableJpaRepositories("com.springboot.repositories") 
@SpringBootApplication(scanBasePackages = { "com.springboot.*"} )
@RestController
@EnableAutoConfiguration
@EntityScan("com.springboot.models") 
public class H2dbApplication implements ApplicationRunner
{
    //Access console : http://localhost:8080/h2-console
    public static void main(String[] args)
    {
        SpringApplication.run(H2dbApplication.class, args);
    }
    
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
    {
        return builder.sources(H2dbApplication.class);

    }
    
    @Override
    public void run(ApplicationArguments arg0) throws Exception
    {
        System.out.println("Hello World from Application Runner");
    }
    
    @GetMapping(value="/hello")
    public String hello()
    {
        return ("Hello from TomCat Application Runner Example");
    }

}
