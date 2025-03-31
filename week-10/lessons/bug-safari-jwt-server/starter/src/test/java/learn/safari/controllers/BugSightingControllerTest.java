package learn.safari.controllers;

import learn.safari.data.BugSightingRepository;
import learn.safari.models.BugOrder;
import learn.safari.models.BugSighting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BugSightingControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    BugSightingRepository repository;

    @Test
    void shouldFindAll() throws Exception {
        List<BugSighting> expected = List.of(
                makeBugSighting(1),
                makeBugSighting(2),
                makeBugSighting(3)
        );

        when(repository.findAll()).thenReturn(expected);

        String expectedJson = TestHelpers.serializeObjectToJson(expected);

        mvc.perform(get("/sighting"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldFindById() throws Exception {
        BugSighting sighting = makeBugSighting(3);

        when(repository.findById(3)).thenReturn(sighting);

        String expectedJson = TestHelpers.serializeObjectToJson(sighting);

        mvc.perform(get("/sighting/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldNotFindByIdAndReturn404() throws Exception {
        mvc.perform(get("/sighting/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldAddValidAndReturn201() throws Exception {
        BugSighting sighting = makeBugSighting(0);
        BugSighting expected = makeBugSighting(7);

        when(repository.add(any())).thenReturn(expected);

        String jsonIn = TestHelpers.serializeObjectToJson(sighting);
        String expectedJson = TestHelpers.serializeObjectToJson(expected);

        var request = post("/sighting")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedJson));
    }

    @Test
    void shouldNotAddInvalidSightingAndReturn412() throws Exception {
        BugSighting sighting = makeBugSighting(0);
        sighting.setInterest(-1);

        String jsonIn = TestHelpers.serializeObjectToJson(sighting);

        var request = post("/sighting")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isPreconditionFailed());
    }

    @Test
    void shouldUpdateValidAndReturn204() throws Exception {
        BugSighting sighting = makeBugSighting(2);

        when(repository.update(any())).thenReturn(true);

        String jsonIn = TestHelpers.serializeObjectToJson(sighting);

        var request = put("/sighting/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldNotUpdateMismatchedIdsAndReturn409() throws Exception {
        BugSighting sighting = makeBugSighting(3);

        String jsonIn = TestHelpers.serializeObjectToJson(sighting);

        var request = put("/sighting/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isConflict());
    }

    @Test
    void shouldNotUpdateInvalidAndReturn412() throws Exception {
        BugSighting sighting = makeBugSighting(2);
        sighting.setDescription(null);

        String jsonIn = TestHelpers.serializeObjectToJson(sighting);

        var request = put("/sighting/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isPreconditionFailed());
    }

    @Test
    void shouldNotUpdateMissingAndReturn404() throws Exception {
        BugSighting sighting = makeBugSighting(99);

        String jsonIn = TestHelpers.serializeObjectToJson(sighting);

        var request = put("/sighting/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonIn);

        mvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAndReturn204() throws Exception {
        when(repository.deleteById(3)).thenReturn(true);

        mvc.perform(delete("/sighting/3"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldNotDeleteMissingAndReturn404() throws Exception {
        mvc.perform(delete("/sighting/99"))
                .andExpect(status().isNotFound());
    }

    private BugSighting makeBugSighting(int id) {
        return new BugSighting(
                id,
                "Stick Bug",
                makeBugOrder(),
                "hanging on the underside of a branch",
                LocalDate.parse("2022-09-23"),
                8.3,
                "https://fake.com/img.jpg");
    }

    private BugOrder makeBugOrder() {
        return new BugOrder(1, "Coleptera", "beetles");
    }
}