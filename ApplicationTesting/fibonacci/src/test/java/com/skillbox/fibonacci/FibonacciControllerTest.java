package com.skillbox.fibonacci;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc

class FibonacciControllerTest extends PostgresTestContainerInitializer {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Test get fibonacci number")
    public void testGetFibonacciNumber() throws Exception {
        mockMvc.perform(get("/fibonacci/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(1));
    }

    @Test
    @DisplayName("Test get negative fibonacci number")
    public void testGetNegativeFibonacciNumber() throws Exception {
        mockMvc.perform(get("/fibonacci/-5"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("Index should be greater or equal to 1"));
    }

}
