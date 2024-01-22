package com.example.restservice;

import java.util.logging.Level;
/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import java.util.logging.Logger;

@SpringBootApplication
@RestController
//@EnableScheduling
@EnableWebMvc
public class RestServiceApplication extends SpringBootServletInitializer
{
    @Value("${spring.application.name}")
    private String name;

    @Value("${server.port}")
    private String port;

    //private static final Logger logger = LoggerFactory.getLogger(RestServiceApplication.class);
    private static final Logger logger = Logger.getLogger(RestServiceApplication.class.getName());

    public static void main(String[] args)
    {
        /*
         * logger.info("this is a info message"); logger.warn("this is a warn message");
         * logger.error("this is a error message");
         */

        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("lang/message");
        messageSource.setDefaultEncoding("UTF-8");

        SpringApplication.run(RestServiceApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
    {
        return builder.sources(RestServiceApplication.class);

    }

    @GetMapping(value = "/")
    public String hello()
    {
        logger.log(Level.INFO, "Default hello Index API is calling");
        return ("Hello from TomCat");
    }

    @RequestMapping("/ConfigProp")
    public String getAppConfig()
    {
        return "The application name: " + name + ", running at port: " + port;
    }

    /**
     * Go to http://localhost:8080/swagger-ui/index.html
     * @return
     */
    @Bean
    public OpenAPI registrationOpenAPI()
    {
        return new OpenAPI()
                .info(new Info().title("Java Doc Rest APIs").description("Rest API documentation").version("1.0"));
    }
    
    /*
     * @Bean public Docket swagger() { return new
     * Docket(DocumentationType.SWAGGER_2).select()
     * .apis(RequestHandlerSelectors.basePackage("com.example.restservice")).build()
     * .forCodeGeneration(true) .apiInfo(new
     * ApiInfoBuilder().title("RestServiceDocu").description("java doc").version(
     * "1.0").build()); }
     */

    /*
     * @Bean public WebMvcConfigurer corsConfigurer() { return new
     * WebMvcConfigurer() { public void addCorsMappings(CorsRegistry registry) {
     * registry.addMapping("/products") .allowedOrigins("http://localhost:8080")
     * //.allowedOrigins("*") .allowedMethods("PUT", "DELETE", "POST", "GET"); } };
     * }
     */
}
