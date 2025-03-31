package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.SecurityClearance;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class SecurityClearanceServiceTest {

    @Autowired
    SecurityClearanceService service;

    @MockBean
    SecurityClearanceRepository repository;

    SecurityClearance EXISTING_SECURITY_CLEARANCE = new SecurityClearance(1, "secret");

    @Test
    void findById() {
        when(repository.findById(1)).thenReturn(EXISTING_SECURITY_CLEARANCE);

        SecurityClearance actual = service.findById(1);

        assertEquals(EXISTING_SECURITY_CLEARANCE, actual);
    }

    @Test
    void findByIdDoesNotFind() {
        when(repository.findById(1)).thenReturn(null);

        SecurityClearance actual = service.findById(1);

        assertNull(actual);
    }

    @Test
    void shouldFindAll() {
        when(repository.findAll()).thenReturn(List.of(EXISTING_SECURITY_CLEARANCE));

        List<SecurityClearance> actual = service.findAll();

        assertEquals(List.of(EXISTING_SECURITY_CLEARANCE), actual);
    }

    @Test
    void shouldAdd() {
        SecurityClearance toAdd = new SecurityClearance(0, "Super Secret");
        SecurityClearance afterAdd = new SecurityClearance(1, "Super Secret");
        when(repository.add(toAdd)).thenReturn(afterAdd);
        Result<SecurityClearance> expected = new Result<>();
        expected.setPayload(afterAdd);

        Result<SecurityClearance> actual = service.add(toAdd);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddWhenNameIsBlank() {
        SecurityClearance toAdd = new SecurityClearance(0, "");
        Result<SecurityClearance> expected = new Result<>();
        expected.addMessage("Name cannot be blank", ResultType.INVALID);

        Result<SecurityClearance> actual = service.add(toAdd);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddWhenNameIsDuplicate() {
        SecurityClearance toAdd = new SecurityClearance(0, "secret");
        when(repository.findAll()).thenReturn(List.of(EXISTING_SECURITY_CLEARANCE));
        Result<SecurityClearance> expected = new Result<>();
        expected.addMessage("Name must be unique", ResultType.INVALID);

        Result<SecurityClearance> actual = service.add(toAdd);

        assertEquals(expected, actual);
    }

    @Test
    void shouldUpdate() {
        SecurityClearance toUpdate = new SecurityClearance(1, "Super Secret");
        when(repository.update(toUpdate)).thenReturn(true);
        Result<SecurityClearance> expected = new Result<>();
        expected.setPayload(toUpdate);

        Result<SecurityClearance> actual = service.update(toUpdate);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateWhenNameIsBlank() {
        SecurityClearance toUpdate = new SecurityClearance(1, "");
        Result<SecurityClearance> expected = new Result<>();
        expected.addMessage("Name cannot be blank", ResultType.INVALID);

        Result<SecurityClearance> actual = service.update(toUpdate);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateWhenNameIsDuplicate() {
        SecurityClearance toUpdate = new SecurityClearance(2, EXISTING_SECURITY_CLEARANCE.getName());
        when(repository.findAll()).thenReturn(List.of(EXISTING_SECURITY_CLEARANCE));
        Result<SecurityClearance> expected = new Result<>();
        expected.addMessage("Name must be unique", ResultType.INVALID);

        Result<SecurityClearance> actual = service.update(toUpdate);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateWhenNotFound() {
        SecurityClearance toUpdate = new SecurityClearance(1, "Super Secret");
        when(repository.update(toUpdate)).thenReturn(false);
        Result<SecurityClearance> expected = new Result<>();
        expected.addMessage("Could not find security clearance for updating", ResultType.NOT_FOUND);

        Result<SecurityClearance> actual = service.update(toUpdate);

        assertEquals(expected, actual);
    }

    @Test
    void shouldDelete() {
        when(repository.findActive()).thenReturn(List.of());
        when(repository.deleteById(1)).thenReturn(true);
        Result<Void> expected = new Result<>();

        Result<Void> actual = service.deleteById(1);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDeleteWhenInUse() {
        when(repository.findActive()).thenReturn(List.of(EXISTING_SECURITY_CLEARANCE));
        Result<Void> expected = new Result<>();
        expected.addMessage("Cannot delete because it is in use", ResultType.INVALID);

        Result<Void> actual = service.deleteById(1);

        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDeleteWhenNotFound() {
        when(repository.findActive()).thenReturn(List.of());
        when(repository.deleteById(1)).thenReturn(false);
        Result<Void> expected = new Result<>();
        expected.addMessage("Could not find for deletion", ResultType.NOT_FOUND);

        Result<Void> actual = service.deleteById(1);

        assertEquals(expected, actual);
    }
}