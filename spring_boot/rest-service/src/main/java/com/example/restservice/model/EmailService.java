package com.example.restservice.model;

public interface EmailService
{
    public String sendSimpleMail(EmailDetails details);
    
    public String sendMailWithAttachment(EmailDetails details);

}
