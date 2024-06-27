package com.example.demo;

import com.example.demo.controllers.MultiplyController;
import com.example.demo.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MultiplyController.class)
class MultiplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getMultiplyPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/multiply"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("multiply"));
    }

    @Test
    void handleMultiplyWithValidInput() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/multiply")
                        .param("number", "10")
                        .param("multiplier", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/result?number=10&multiplier=5&result=50"));
    }

    @Test
    void handleMultiplyWithInvalidInput() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/multiply")
                        .param("number", "invalid")
                        .param("multiplier", "invalid"))
                .andExpect(status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.view().name("error_500"));
    }

    @Test
    void getResultPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/result")
                        .param("number", "10")
                        .param("multiplier", "5")
                        .param("result", "50"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("result"))
                .andExpect(MockMvcResultMatchers.model().attribute("number", 10))
                .andExpect(MockMvcResultMatchers.model().attribute("multiplier", 5))
                .andExpect(MockMvcResultMatchers.model().attribute("result", 50));
    }
}