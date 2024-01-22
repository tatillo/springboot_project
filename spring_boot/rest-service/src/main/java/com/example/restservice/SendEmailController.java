package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.model.EmailDetails;
import com.example.restservice.model.EmailService;

@RestController
//https://www.geeksforgeeks.org/spring-boot-sending-email-via-smtp/
public class SendEmailController
{
    @Autowired
    EmailService emailService;
    
    @PostMapping(value="/simpleEmail")
    public String sendEmail(@RequestBody EmailDetails details)
    {
        return (emailService.sendSimpleMail(details));
    }
    
    @PostMapping(value="/mailwithattachment")
    public String sendMailWithAttachment(@RequestBody EmailDetails details)
    {
        return (emailService.sendMailWithAttachment(details));
    }
    
    /**
     * sample json format to send via postman
     * {
        "recipient": "rezaatillo@gmail.com",
        "msgBody": "Hi Theresa, I've attached the document. Kindly check. Thanks!",
        "subject": "Spring Boot Email with attachment",
        "attachment": "C:\\Work\\images\\labrador.jpg"
        }
     */
}
