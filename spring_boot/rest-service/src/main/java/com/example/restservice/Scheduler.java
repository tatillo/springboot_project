package com.example.restservice;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler
{
   @Scheduled(fixedDelay = 1000, initialDelay = 3000)
   public void fixedDelaySch() 
   {
      System.out.println("Fixed Delay scheduler:: " + LocalDateTime.now().toLocalTime());
   }
}