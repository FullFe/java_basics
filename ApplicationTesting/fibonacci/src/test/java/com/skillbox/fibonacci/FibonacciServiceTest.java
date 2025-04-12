package com.skillbox.fibonacci;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FibonacciServiceTest {

    private final FibonacciRepository repository = mock(FibonacciRepository.class);

    private final FibonacciCalculator calculator = new FibonacciCalculator();

    @InjectMocks
    private final FibonacciService service = new FibonacciService(repository, calculator);



    @Test
    @DisplayName("Test taking number from DB")
    public void TestServiceTakeNumberFromDB(){
        FibonacciNumber number = new FibonacciNumber();
        number.setIndex(10);
        number.setValue(55);
        when(repository.findByIndex(10)).thenReturn(Optional.of(number));
        FibonacciNumber actual = service.fibonacciNumber(10);
        assertEquals(number, actual);
        verify(repository, times(1)).findByIndex(10);
        verify(repository, times(0)).save(number);
    }

    @Test
    @DisplayName("Test taking number from Calculator")
    public void TestServiceTakeNumberFromCalculator(){
        when(repository.findByIndex(12)).thenReturn(Optional.empty());
       // when(calculator.getFibonacciNumber(12)).thenReturn(144);
        FibonacciNumber actual = service.fibonacciNumber(12);
        assertEquals(144, actual.getValue());
        verify(repository, times(1)).findByIndex(12);
        verify(repository, times(1)).save(actual);
        //verify(calculator, times(1)).getFibonacciNumber(12);
    }


    @Test
    @DisplayName("Test index below 0")
    public void TestServiceExceptionThrow(){
        assertThrows(IllegalArgumentException.class ,()-> service.fibonacciNumber(-5));
    }
}
