package com.mockito.junitcalculator;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BddTester
{
    private MathApplication mathApp;
    private CalculatorService calService;

    @Before
    public void setup()
    {
        mathApp = new MathApplication();
        calService = mock(CalculatorService.class);
        mathApp.setCalculatorService(calService);
    }

    @After
    public void tearDown()
    {
        mathApp = null;
        calService = null;
    }

    @Test
    public void testUsingBDD()
    {
        System.out.println("Running testUsingBDD");
        
        // Given
        given(calService.add(20.0, 10.0)).willReturn(30.0);

        // when
        double result = mathApp.add(20.0, 10.0);

        // then
        assertEquals(result, 30.0, 0);
        
        testJava();
    }
    
    public void testJava()
    {
        String test = "Theresa";
        String reverse = "";
        for(int i = test.length() - 1; i >= 0; i--)
        {
            reverse += test.charAt(i);
        }
        
        System.out.println(reverse);
        
        SortedSet<Integer> numberSet = new TreeSet<Integer>();
        numberSet.add(2);
        numberSet.add(1);
        numberSet.add(10);
        numberSet.add(5);
        
        System.out.println(numberSet);
        
    }
}
