package com.mockito.junitcalculator;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class MathSpyTester
{
    private MathApplication mathApplication;
    private CalculatorService calcService;

    @Before
    public void setUp()
    {
        mathApplication = new MathApplication();
        Calculator calculator = new Calculator();
        
        //use spy to call the real implementation
        calcService = spy(calculator);
        mathApplication.setCalculatorService(calcService);
    }

    @Test
    public void testAdd()
    {
        // perform operation on real object
        // test the add functionality
        Assert.assertEquals(mathApplication.add(20.0, 10.0), 30.0, 0);
    }

    class Calculator implements CalculatorService
    {
        @Override
        public double add(double input1, double input2)
        {
            System.out.println("CalculatorService add");
            return input1 + input2;
        }

        @Override
        public double subtract(double input1, double input2)
        {
            throw new UnsupportedOperationException("Method not implemented yet!");
        }

        @Override
        public double multiply(double input1, double input2)
        {
            throw new UnsupportedOperationException("Method not implemented yet!");
        }

        @Override
        public double divide(double input1, double input2)
        {
            throw new UnsupportedOperationException("Method not implemented yet!");
        }
    }
}
