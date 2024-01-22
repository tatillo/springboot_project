package com.mockito.junitcalculator;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import junit.framework.Assert;

// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTester
{
    // @InjectMocks annotation is used to create and inject the mock object
    // Can also use the @TestSubject?
    @InjectMocks
    MathApplication mathApplication = new MathApplication();

    // @Mock annotation is used to create the mock object to be injected
    @Mock
    CalculatorService calcService;

    /*
     * 2. Another way of doing a mock is to have a setup and calling mock() method
     * private MathApplication mathApplication; private CalculatorService
     * calcService;
     * 
     * @Before public void setup() {
     * System.out.println("setup call for MathApplicationTester"); mathApplication =
     * new MathApplication(); calcService = mock(CalculatorService.class);
     * mathApplication.setCalculatorService(calcService); }
     * 
     * @After public void tearDown() {
     * System.out.println("tearDown call for MathApplicationTester"); }
     */

    @Test
    public void testAdd()
    {
        System.out.println("Running testAdd BEGIN");
        // add the behavior of calc service to add two numbers
        when(calcService.add(10.0, 20.0)).thenReturn(30.00);

        // resets the service
        // reset(calcService);

        // test the add functionality
        assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);

        // verify call to add method to be completed within 100 ms
        verify(calcService, timeout(100)).add(10.0, 20.0);
    }

    @Test
    public void testAddWithVerify()
    {
        System.out.println("Running testAddWithVerify BEGIN");
        // add the behavior of calc service to add two numbers
        when(calcService.add(10.0, 20.0)).thenReturn(30.00);

        // test the add functionality
        assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);

        // verify the behavior
        verify(calcService).add(10.0, 20.0);
    }

    @Test
    public void testAddVerifyTimes()
    {
        System.out.println("Running testAddVerifyTimes BEGIN");

        // add the behavior of calc service to add two numbers
        when(calcService.add(10.0, 20.0)).thenReturn(30.00);
        when(calcService.subtract(10.0, 10.0)).thenReturn(0.00);

        // add the behavior of calc service to subtract two numbers
        when(calcService.subtract(20.0, 10.0)).thenReturn(10.00);

        // test the add functionality
        assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);
        assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);
        assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);

        // test the subtract functionality
        assertEquals(mathApplication.subtract(20.0, 10.0), 10.0, 0.0);

        // default call count is 1
        verify(calcService).subtract(20.0, 10.0);

        assertEquals(mathApplication.subtract(10.0, 10.0), 00.0, 0.0);
        verify(calcService).subtract(10.0, 10.0);

        // check if add function is called three times
        verify(calcService, times(3)).add(10.0, 20.0);

        verify(calcService, times(1)).subtract(10.0, 10.0);
        verify(calcService, times(1)).subtract(20.0, 10.0);

        // verify that method was never called on a mock
        verify(calcService, never()).multiply(10.0, 20.0);
    }

    @Test
    public void testAtTimes()
    {
        System.out.println("Running testAtTimes BEGIN");

        when(calcService.add(10.0, 20.0)).thenReturn(30.00);
        assertEquals(mathApplication.add(10.0, 20.0), 30.00, 0);
        verify(calcService, atLeastOnce()).add(10.0, 20.0);

        when(calcService.subtract(10.0, 10.0)).thenReturn(0.00);
        assertEquals(mathApplication.subtract(10.0, 10.0), 00.0, 0.0);
        verify(calcService, atMost(2)).subtract(10.0, 10.0);
        // verify(calcService, atLeast(2)).subtract(10.0, 10.0);
    }

    @Test(expected = RuntimeException.class)
    public void throwException()
    {
        System.out.println("Running throwException BEGIN");

        doThrow(new RuntimeException("Exception encountered: Expected double")).
            when(calcService.add(0, 5.00));
        
        when(calcService.add(0, 5.00)).
            thenThrow(new RuntimeException("Exception encountered: Expected double"));

        /*
         * Can also do it this way when(calcService.add(0, 5.00)). thenThrow(new
         * RuntimeException("Exception encountered: Expected double"));
         */
        assertEquals(mathApplication.add(0, 5.00), 5.0);
    }

    // @Test
    // This is going to cause an exception due to the order
    public void testInOrder()
    {
        System.out.println("Running testInOrder BEGIN");
        // add the behavior to add numbers
        when(calcService.add(20.0, 10.0)).thenReturn(30.0);

        // subtract the behavior to subtract numbers
        when(calcService.subtract(20.0, 10.0)).thenReturn(10.0);

        // test the add functionality
        Assert.assertEquals(mathApplication.add(20.0, 10.0), 30.0, 0);

        // test the subtract functionality
        Assert.assertEquals(mathApplication.subtract(20.0, 10.0), 10.0, 0);

        // create an inOrder verifier for a single mock
        InOrder inOrder = inOrder(calcService);

        // following will make sure that add is first called then subtract is called.
        inOrder.verify(calcService).subtract(20.0, 10.0);
        inOrder.verify(calcService).add(20.0, 10.0);
    }

    @Test
    public void callbackAnswer()
    {
        System.out.println("Running callbackAnswer BEGIN");

        // add the behavior to add numbers
        when(calcService.add(20.0, 10.0)).thenAnswer(new Answer<Double>()
        {
            @Override
            public Double answer(InvocationOnMock invocation) throws Throwable
            {
                // get the arguments passed to mock
                Object[] args = invocation.getArguments();
                System.out.println("Arguments: " + args[0] + " and: " + args[1]);

                // get the mock
                Object mock = invocation.getMock();
                System.out.println("mock: " + mock.toString());

                // return the result
                return 30.0;
            }
        });

        // test the add functionality
        Assert.assertEquals(mathApplication.add(20.0, 10.0), 30.0, 0);
    }
}