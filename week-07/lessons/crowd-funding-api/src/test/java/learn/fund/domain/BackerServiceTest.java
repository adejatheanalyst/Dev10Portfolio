package learn.fund.domain;

import learn.fund.data.BackerRepository;
import learn.fund.models.Backer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static learn.fund.TestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BackerServiceTest {

    @MockBean
    BackerRepository repository;

    @Autowired
    BackerService service;

    // add
    @Test
    void shouldAdd() {
        Backer arg = makeBacker(5);
        arg.setBackerId(0);

        when(repository.add(any())).thenReturn(makeBacker(1));

        Result<Backer> expected = makePayloadResult(makeBacker(1));
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddWithNullBacker() {
        Result<Backer> expected = makeInvalidResult("backer may not be null");
        Result<Backer> actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullName() {
        Backer arg = makeBacker(0);
        arg.setName(null);
        Result<Backer> expected = makeInvalidResult("name is required");
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyName() {
        Backer arg = makeBacker(0);
        arg.setName(" ");
        Result<Backer> expected = makeInvalidResult("name is required");
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullEmail() {
        Backer arg = makeBacker(0);
        arg.setEmailAddress(null);
        Result<Backer> expected = makeInvalidResult("email is required");
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyEmail() {
        Backer arg = makeBacker(0);
        arg.setEmailAddress("  ");
        Result<Backer> expected = makeInvalidResult("email is required");
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddInvalidEmail() {
        Backer arg = makeBacker(0);
        arg.setEmailAddress("notvalid");
        Result<Backer> expected = makeInvalidResult("not a valid email address");
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicateEmail() {
        Backer arg = makeBacker(0);
        arg.setEmailAddress("backer1@example.com");

        when(repository.findByEmailAddress(any())).thenReturn(makeBacker(1));

        Result<Backer> expected = makeInvalidResult("duplicate email address");
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    // update
    @Test
    void shouldUpdate() {
        Backer arg = makeBacker(1);

        when(repository.update(any())).thenReturn(true);

        Result<?> expected = makePayloadResult(null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateWithNullBacker() {
        Result<?> expected = makeInvalidResult("backer may not be null");
        Result<?> actual = service.update(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullName() {
        Backer arg = makeBacker(1);
        arg.setName(null);
        Result<?> expected = makeInvalidResult("name is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateEmptyName() {
        Backer arg = makeBacker(1);
        arg.setName(" ");
        Result<?> expected = makeInvalidResult("name is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullEmail() {
        Backer arg = makeBacker(1);
        arg.setEmailAddress(null);
        Result<?> expected = makeInvalidResult("email is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateEmptyEmail() {
        Backer arg = makeBacker(1);
        arg.setEmailAddress("  ");
        Result<?> expected = makeInvalidResult("email is required");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidEmail() {
        Backer arg = makeBacker(1);
        arg.setEmailAddress("notvalid");
        Result<?> expected = makeInvalidResult("not a valid email address");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateDuplicateEmail() {
        Backer arg = makeBacker(2);
        arg.setEmailAddress("backer1@example.com");

        when(repository.findByEmailAddress(any())).thenReturn(makeBacker(1));

        Result<?> expected = makeInvalidResult("duplicate email address");
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidId() {
        Backer arg = makeBacker(2);
        Result<?> expected = makeNotFoundResult();
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    // delete
    @Test
    void shouldDelete() {
        when(repository.deleteById(anyInt())).thenReturn(true);
        Result<?> expected = makePayloadResult(null);
        Result<?> actual = service.deleteById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDelete() {
        Result<?> expected = makeNotFoundResult();
        Result<?> actual = service.deleteById(2);
        assertEquals(expected, actual);
    }
}