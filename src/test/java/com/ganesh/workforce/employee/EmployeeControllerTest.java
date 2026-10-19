package com.ganesh.workforce.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {
    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;

    @Test void createsAndRetrievesEmployee() throws Exception {
        EmployeeRequest request = new EmployeeRequest("Test", "Employee",
            "test." + System.nanoTime() + "@example.com", "Engineering", "Developer");
        String response = mockMvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated()).andExpect(jsonPath("$.firstName").value("Test"))
            .andReturn().getResponse().getContentAsString();
        long id = objectMapper.readTree(response).get("id").asLong();
        mockMvc.perform(get("/api/employees/{id}", id)).andExpect(status().isOk())
            .andExpect(jsonPath("$.lastName").value("Employee"));
    }

    @Test void rejectsInvalidEmployeeRequest() throws Exception {
        String body = "{\"firstName\":\"\",\"lastName\":\"Person\",\"email\":\"bad-email\",\"department\":\"IT\",\"role\":\"Analyst\"}";
        mockMvc.perform(post("/api/employees").contentType(MediaType.APPLICATION_JSON).content(body))
            .andExpect(status().isBadRequest());
    }
}
