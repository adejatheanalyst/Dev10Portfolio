package learn.fund.domain;

import learn.fund.data.BackerRepositoryDouble;
import learn.fund.models.Backer;
import org.junit.jupiter.api.Test;

import static learn.fund.TestHelper.makeBacker;
import static learn.fund.TestHelper.makeResult;
import static org.junit.jupiter.api.Assertions.*;

class BackerServiceTest {

    BackerService service = new BackerService(new BackerRepositoryDouble());

    // add
    @Test
    void shouldAdd() {
        Backer arg = makeBacker(5);
        arg.setBackerId(0);
        Result<Backer> expected = makeResult(null, makeBacker(1));
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddWithNullBacker() {
        Result<Backer> expected = makeResult("backer may not be null", null);
        Result<Backer> actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullName() {
        Backer arg = makeBacker(0);
        arg.setName(null);
        Result<Backer> expected = makeResult("name is required", null);
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyName() {
        Backer arg = makeBacker(0);
        arg.setName(" ");
        Result<Backer> expected = makeResult("name is required", null);
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullEmail() {
        Backer arg = makeBacker(0);
        arg.setEmailAddress(null);
        Result<Backer> expected = makeResult("email is required", null);
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddEmptyEmail() {
        Backer arg = makeBacker(0);
        arg.setEmailAddress("  ");
        Result<Backer> expected = makeResult("email is required", null);
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddInvalidEmail() {
        Backer arg = makeBacker(0);
        arg.setEmailAddress("notvalid");
        Result<Backer> expected = makeResult("not a valid email address", null);
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddDuplicateEmail() {
        Backer arg = makeBacker(0);
        arg.setEmailAddress("backer1@example.com");
        Result<Backer> expected = makeResult("duplicate email address", null);
        Result<Backer> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    // update
    @Test
    void shouldUpdate() {
        Backer arg = makeBacker(1);
        Result<?> expected = makeResult(null, null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateWithNullBacker() {
        Result<?> expected = makeResult("backer may not be null", null);
        Result<?> actual = service.update(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullName() {
        Backer arg = makeBacker(1);
        arg.setName(null);
        Result<?> expected = makeResult("name is required", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateEmptyName() {
        Backer arg = makeBacker(1);
        arg.setName(" ");
        Result<?> expected = makeResult("name is required", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateNullEmail() {
        Backer arg = makeBacker(1);
        arg.setEmailAddress(null);
        Result<?> expected = makeResult("email is required", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateEmptyEmail() {
        Backer arg = makeBacker(1);
        arg.setEmailAddress("  ");
        Result<?> expected = makeResult("email is required", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidEmail() {
        Backer arg = makeBacker(1);
        arg.setEmailAddress("notvalid");
        Result<?> expected = makeResult("not a valid email address", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateDuplicateEmail() {
        Backer arg = makeBacker(2);
        arg.setEmailAddress("backer1@example.com");
        Result<?> expected = makeResult("duplicate email address", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotUpdateInvalidId() {
        Backer arg = makeBacker(2);
        Result<?> expected = makeResult("could not update backer id 2", null);
        Result<?> actual = service.update(arg);
        assertEquals(expected, actual);
    }

    // delete
    @Test
    void shouldDelete() {
        Result<?> expected = makeResult(null, null);
        Result<?> actual = service.deleteById(1);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotDelete() {
        Result<?> expected = makeResult("could not delete backer id 2", null);
        Result<?> actual = service.deleteById(2);
        assertEquals(expected, actual);
    }
}