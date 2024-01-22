package com.example.restservice.application;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandRunnerExample implements CommandLineRunner
{
    public static void main(String args[])
    {
        SpringApplication.run(CommandRunnerExample.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        System.out.println("Hello world from Command Line Runner: " + Arrays.toString(args));
    }

}
