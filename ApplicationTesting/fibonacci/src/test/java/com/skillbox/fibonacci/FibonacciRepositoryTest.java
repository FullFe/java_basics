package com.skillbox.fibonacci;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FibonacciRepositoryTest extends PostgresTestContainerInitializer {

    @Autowired
    FibonacciRepository repository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    EntityManager entityManager;


    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Test save new fibonacci number")
    void TestSaveNewFibonacci() {
        FibonacciNumber number = new FibonacciNumber(6,8);

        repository.save(number);
        entityManager.flush();
        List<FibonacciNumber> actual = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number WHERE index = 6",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );
        assertEquals(number.getValue(),actual.get(0).getValue());
        entityManager.detach(actual.get(0));
        List<FibonacciNumber> actual2 = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );

        for (FibonacciNumber fibonacciNumber : actual2) {
            System.out.println(fibonacciNumber.getValue());
        }
    }
    @Test
    @DisplayName("Test save duplicate of number")
    public void TestRepeatedSaveFibonacci(){
        FibonacciNumber number = new FibonacciNumber(7,13);
        for(int i =0; i<2; i++){
            repository.save(number);
            entityManager.flush();
            entityManager.detach(number);
        }
        List<FibonacciNumber> numbers = jdbcTemplate.query(
                "SELECT * FROM fibonacci_number",
                (rs, rowNum) -> new FibonacciNumber(rs.getInt("index"), rs.getInt("value"))
        );
        for (FibonacciNumber fibonacciNumber : numbers) {
            System.out.println(fibonacciNumber.getValue());
        }
    }

    @Test
    @DisplayName("Test method findByIndex")
    public void TestFindByIndex(){
        for(int i =1; i<10; i++){
            FibonacciCalculator calculator = new FibonacciCalculator();
            int value = calculator.getFibonacciNumber(i);
            FibonacciNumber number = new FibonacciNumber(i,value);
            repository.save(number);
            entityManager.flush();
            entityManager.detach(number);
        }

        assertEquals(5,repository.findByIndex(5).orElseThrow(RuntimeException::new).getValue());
    }

}
