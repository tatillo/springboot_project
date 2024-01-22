package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController
{

    @GetMapping("/welcome")
    public String displayMessage()
    {
        return "Welcome home theresa! We are glad to have you here in the Netherlands";
    }
}
