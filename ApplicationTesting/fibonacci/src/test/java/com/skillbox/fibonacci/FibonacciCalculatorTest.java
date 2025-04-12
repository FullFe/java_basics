package com.skillbox.fibonacci;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FibonacciCalculatorTest {

    private static FibonacciCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new FibonacciCalculator();
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 6, 7, 8, 9})
    @DisplayName("Test successful execution")
    public void testCalculatorSuccessfulExecution(int number){
        int expected = computeFibonacci(number);
        assertEquals(expected, calculator.getFibonacciNumber(number));
    }

    private int computeFibonacci(int number){
        int result = 1;
        if(number < 1){
            throw new IllegalArgumentException("Number must be greater than 0");
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        for(int i = 2; i < number; i++){
            result = list.get(i - 1) + list.get(i-2);
            list.add(result);
        }
        return result;
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2})
    @DisplayName("Test 1 and 2 indexes of Fibonacci row")
    public void testCalculatorFirstNSecondNumber(int number){
        int expected = computeFibonacci(number);
        assertEquals(expected, calculator.getFibonacciNumber(number));
    }

    @Test
    @DisplayName("Test throwing exception caused by wrong index")
    public void testCalculatorExceptionThrow(){
        assertThrows(IllegalArgumentException.class, () -> calculator.getFibonacciNumber(-5));
    }
}
