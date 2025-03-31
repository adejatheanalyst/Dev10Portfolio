package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class AliasServiceTest {
    @Autowired
    AliasService service;

    @MockBean
    AliasRepository repository;

    Agent existingAgent = new Agent(1, "Hazel", "C", "Sauven", LocalDate.of(1954,9, 16), 76);
    Agent NonExistingAgent = new Agent(2, "James", "B", "Bond", LocalDate.of(1986,1, 16), 86);
    List<Alias> existingAliasList = List.of(new Alias ( 1, "Nocturnal", "The Ghost", existingAgent));
    Alias exisitingAlias = new Alias(2, "Nocturnal", "The Ghost", existingAgent);
    Alias NonExisitingAlias = new Alias(2, "test", "test-persona", existingAgent);
    Alias NonExistingAliasTooAddNullNam = new Alias(4, null, "test-persona", existingAgent);
    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(existingAliasList);
        List<Alias> actual = service.findAll();

        assertEquals(existingAliasList, actual);
    }

    @Test
    void findById() {
        when(repository.findById(1)).thenReturn(exisitingAlias);
        Alias actual = service.findById(1);
        assertEquals(exisitingAlias, actual);
    }
    @Test
    void findByIdFail() throws DataAccessException {
        when(repository.findById(5)).thenReturn(null);
        Alias actual = service.findById(5);
        assertNull(actual);
    }

    @Test
    void ShouldAdd() {
        Alias toAdd = new Alias(0, "Test","Test2", existingAgent);
        Alias afterAdd = NonExisitingAlias;
        when(repository.add(toAdd)).thenReturn(afterAdd);
        Result<Alias> expected = new Result<>();
        expected.setPayload(afterAdd);
        Result<Alias> actual = service.add(toAdd);

        assertEquals(expected, actual);
    }
    @Test
    void ShouldNotAddNullName(){
        Alias toAdd = NonExistingAliasTooAddNullNam;
        Result<Alias> expected = new Result<>();
        expected.addMessage("Name is required", ResultType.INVALID);
        Result<Alias> actual = service.add(toAdd);
        assertEquals(expected, actual);
    }
    @Test
    void ShouldNotAddDuplicateNamePersonaRequired() {
        Alias toAdd = new Alias(3, "Nocturnal", "", existingAgent);
        when(repository.findAll()).thenReturn(existingAliasList);
        Result<Alias> expected = new Result<>();
        expected.addMessage("Duplicate name must have persona.", ResultType.INVALID);
        Result<Alias> actual = service.add(toAdd);
        assertEquals(expected, actual);
    }

    @Test
    void ShouldUpdate(){
        Alias toUpdate = new Alias(1, "Test", "Test-persona", existingAgent);
       when(repository.update(toUpdate)).thenReturn(true);
       Result<Alias> actual = service.update(toUpdate);
       assertEquals(ResultType.SUCCESS, actual.getType());
    }
    @Test
    void ShouldNotUpdateNullName(){
        Alias toUpdate = new Alias(1, "", "Test-persona", existingAgent);
        when(repository.update(toUpdate)).thenReturn(false);
        Result<Alias> actual = service.update(toUpdate);
        assertEquals(ResultType.INVALID, actual.getType());
    }
    @Test
    void ShouldNotUpdateDuplicateNamePersonaRequired(){
        Alias toUpdate = new Alias(1, "Nocturnal", "", existingAgent);
        when(repository.findAll()).thenReturn(existingAliasList);
        Result<Alias> actual = service.update(toUpdate);
        assertEquals(ResultType.INVALID, actual.getType());
    }
    @Test
    void ShouldNotUpdateNonExistingAliasId(){
        Alias toUpdate = new Alias(6, "Test", "Test", existingAgent);
        when(repository.update(toUpdate)).thenReturn(false);
        Result<Alias> actual = service.update(toUpdate);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }
    @Test
    void ShouldNotUpdateNonExistingAgentId(){
        Alias toUpdate = new Alias(1, "Test", "Test", NonExistingAgent);
        when(repository.update(toUpdate)).thenReturn(false);
        Result<Alias> actual = service.update(toUpdate);
        assertEquals(ResultType.NOT_FOUND, actual.getType());
    }
    @Test
    void ShouldDelete(){
       when(repository.deleteById(1)).thenReturn(true);
       Result<Void> expected = new Result<>();
       Result<Void> actual = service.deleteById(1);
        assertEquals(expected, actual);
    }
    @Test
    void ShouldNotDeleteNotFound(){
        when(repository.deleteById(5)).thenReturn(false);
        Result<Void> actual = service.deleteById(1);
        assertEquals(ResultType.NOT_FOUND, actual.getType());

    }
}