package learn.foraging.domain;

import learn.foraging.data.ForageRepository;
import learn.foraging.data.ForageRepositoryDouble;
import learn.foraging.data.ForagerRepositoryDouble;
import learn.foraging.data.ItemRepositoryDouble;
import learn.foraging.models.Forage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;

import static learn.foraging.TestData.*;
import static learn.foraging.TestHelper.makeResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ForageServiceTest {
    @Autowired
            ForageService service;
    @MockBean
    ForageRepository repository;

//    ForageService service = new ForageService(
//            new ForageRepositoryDouble(),
//            new ForagerRepositoryDouble(),
//            new ItemRepositoryDouble());

    @Test
    void shouldAdd() {
        Forage arg = new Forage(0, JAN_01_2023, FORAGER_ONE, EDIBLE, ONE_KILO);
        Result<Forage> expected = new Result<>();
        expected.setResultType(ResultType.SUCCESS);
        Result<Forage> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullForage() {
        Result<Forage> expected = makeResult("Nothing to save.", null);
        Result<Forage> actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullDate() {
        Forage arg = new Forage(0, null, FORAGER_ONE, EDIBLE, ONE_KILO);
        Result<Forage> expected = makeResult("Forage date is required.", null);
        expected.setResultType(ResultType.INVALID);
        Result<Forage> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullForager() {
        Forage arg = new Forage(0, JAN_01_2023, null, EDIBLE, ONE_KILO);
        Result<Forage> expected = makeResult("Forager is required.", null);
        expected.setResultType(ResultType.INVALID);
        Result<Forage> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullItem() {
        Forage arg = new Forage(0, JAN_01_2023, FORAGER_ONE, null, ONE_KILO);
        Result<Forage> expected = makeResult("Item is required.", null);
        expected.setResultType(ResultType.INVALID);
        Result<Forage> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullKilograms() {
        Forage arg = new Forage(0, JAN_01_2023, FORAGER_ONE, EDIBLE, null);
        Result<Forage> expected = makeResult("Kilograms are required.", null);
        expected.setResultType(ResultType.INVALID);
        Result<Forage> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddFutureDate() {
        Forage arg = new Forage(0, LocalDate.now().plusDays(35), FORAGER_ONE, EDIBLE, ONE_KILO);
        Result<Forage> expected = makeResult("Forage date cannot be in the future.", null);
        expected.setResultType(ResultType.INVALID);
        Result<Forage> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddKilosLessThanEqualToZero() {
        Forage arg = new Forage(0, JAN_01_2023, FORAGER_ONE, EDIBLE, BigDecimal.ZERO);
        Result<Forage> expected = makeResult("Kilograms must be a positive number less than 250.0", null);
        expected.setResultType(ResultType.INVALID);
        Result<Forage> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddKilosTooLarge() {
        Forage arg = new Forage(0, JAN_01_2023, FORAGER_ONE, EDIBLE, new BigDecimal("250.1"));
        Result<Forage> expected = makeResult("Kilograms must be a positive number less than 250.0", null);
        expected.setResultType(ResultType.INVALID);
        Result<Forage> actual = service.add(arg);
        assertEquals(expected, actual);
    }


    @Test
    void shouldNotAddWhenItemNotFound() {
        Forage arg = new Forage(0, JAN_01_2023, FORAGER_ONE, null, ONE_KILO);
        when(repository.add(arg)).thenReturn(null);
        Result<Forage> expected = new Result<>();
        expected.setResultType(ResultType.INVALID);
        Result<Forage> actual = service.add(arg);
        assertEquals(expected.getResultType(), actual.getResultType());

    }


}