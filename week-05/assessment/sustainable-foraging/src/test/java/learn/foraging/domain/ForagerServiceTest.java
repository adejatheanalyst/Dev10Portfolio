package learn.foraging.domain;

import learn.foraging.data.ForagerRepository;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static learn.foraging.TestData.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ForagerServiceTest {
    @Autowired
    ForagerService service;
    @MockBean
    ForagerRepository repository;

    @Test
    void findById() {
        Forager expected = FORAGER_ONE;
        when(repository.findById(1)).thenReturn(expected);
        Forager actual = service.findById(1).getPayload();
        assertEquals(expected, actual);
    }

    @Nested class shouldAdd {
    @Test
    void add() {
        Forager forager = new Forager(0, "Test", "Testerson", "TT");
        Forager expected = new Forager(3, "Test", "Testerson", "TT");
        when(repository.create(forager)).thenReturn(expected);
        Forager actual = service.add(forager).getPayload();
        assertEquals(expected, actual);
    }
    @Test
    void shouldNotAddNullForager() {
        Forager forager = null;
        Result<Forager> expected = new Result<>();
        expected.addErrorMessage("Forager cannot be null.");
        expected.setResultType(ResultType.INVALID);
        Result<Forager> actual = service.add(forager);
        assertEquals(expected, actual);
    }
    @Test
    void shouldNotAddNullFirstName(){
        Forager forager = new Forager(0, null, "Testerson", "TT");
        Result<Forager> expected = new Result<>();
        expected.addErrorMessage("Forager `firstName` is required.");
        expected.setResultType(ResultType.INVALID);
        Result<Forager> actual = service.add(forager);
        assertEquals(expected, actual);

    }
    @Test
    void shouldNotAddNullLastName(){
        Forager forager = new Forager(0, "test", null, "TT");
        Result<Forager> expected = new Result<>();
        expected.addErrorMessage("Forager `lastName` is required.");
        expected.setResultType(ResultType.INVALID);
        Result<Forager> actual = service.add(forager);
        assertEquals(expected, actual);

    }
    @Test
    void shouldNotAddNullState(){
        Forager forager = new Forager(0, "test", "null", null);
        Result<Forager> expected = new Result<>();
        expected.addErrorMessage("Forager `state` is required.");
        expected.setResultType(ResultType.INVALID);
        Result<Forager> actual = service.add(forager);
        assertEquals(expected, actual);

    }
    @Test
    void shouldNotAddSetId() {
        Forager forager = new Forager(1, "Test", "Testerson", "TT");
        Result<Forager> expected = new Result<>();
        expected.addErrorMessage("Forager `id` cannot be set for `add` operation.");
        expected.setResultType(ResultType.INVALID);
        Result<Forager> actual = service.add(forager);
        assertEquals(expected, actual);

    }
}

    @Test
    void deleteById() {
    Forager forager = new Forager(1, "Test", "Testerson", "TT");
    when(repository.deleteById(1)).thenReturn(true);
    Result<Forager> actual = service.deleteById(1);
    assertTrue(actual.isSuccess());
    }
@Nested class shouldFind {
    @Test
    void findByState() {
        Forager forager = new Forager(1, "Test", "Testerson", "TT");
        when(repository.findByState("TT")).thenReturn(List.of(forager));
        List<Forager> actual = service.findByState("TT");
        assertEquals(1, actual.size());
    }

    @Test
    void findByLastName() {
        Forager forager = new Forager(1, "Test", "Testerson", "TT");
        when(repository.findByLastName("Test")).thenReturn(List.of(forager));
        List<Forager> actual = service.findByLastName("Test");
        assertEquals(1, actual.size());
    }
}
}