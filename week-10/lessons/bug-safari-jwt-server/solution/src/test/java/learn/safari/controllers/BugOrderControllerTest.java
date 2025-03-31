package learn.safari.controllers;

import learn.safari.data.BugOrderRepository;
import learn.safari.models.BugOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BugOrderControllerTest {

    @MockBean
    BugOrderRepository repository;

    @Autowired
    MockMvc mvc;

    @Test
    void shouldFindAll() throws Exception {
        List<BugOrder> bugOrders = List.of(
                new BugOrder(2, "Lepidoptera", "butterflies, moths"),
                new BugOrder(1, "Coleptera", "beetles")
        );

        when(repository.findAll()).thenReturn(bugOrders);

        String expectedJson = TestHelpers.serializeObjectToJson(bugOrders);

        mvc.perform(get("/order"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }
}