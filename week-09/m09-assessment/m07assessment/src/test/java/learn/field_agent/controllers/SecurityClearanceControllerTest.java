package learn.field_agent.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.field_agent.domain.SecurityClearanceService;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.RequestEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
class SecurityClearanceControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    SecurityClearanceService service;


    @Test
    void findById() throws Exception {

    }

    @Test
    void findAll() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/security_clearance");
        List<SecurityClearance> securityClearances = List.of(new SecurityClearance(1, "Secret"));
        ObjectMapper jsonMapper = new ObjectMapper();
        String securityClearanceJson = jsonMapper.writeValueAsString(securityClearances);
        when(service.findAll()).thenReturn(securityClearances);

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(securityClearanceJson));
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}