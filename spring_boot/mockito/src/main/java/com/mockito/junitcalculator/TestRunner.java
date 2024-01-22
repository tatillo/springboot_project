package com.mockito.junitcalculator;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner
{
    public static void main(String[] args)
    {
        runTestClasses(MathApplicationTester.class);
        runTestClasses(MathSpyTester.class);
        runTestClasses(BddTester.class);
    }
    
    public static void runTestClasses(Class<?>... classes)
    {
        System.out.println("---- Running testcase: " + classes.getClass().getPackageName());
        
        Result resultUsingInject = JUnitCore.runClasses(classes);
        for (Failure failure : resultUsingInject.getFailures())
        {
            System.out.println("Failed: " + failure.toString());
        }
        System.out.println("Test was a successful: " + resultUsingInject.wasSuccessful());
        System.out.println("Test count: " + resultUsingInject.getRunCount());
        
        System.out.println("------ End testcase-------");
        System.out.println(System.lineSeparator());
    }
}
