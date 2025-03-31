package learn.safari.domain;

import learn.safari.data.BugOrderRepository;
import learn.safari.data.BugSightingRepository;
import learn.safari.models.BugOrder;
import learn.safari.models.BugSighting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BugSightingServiceTest {

    @MockBean
    BugSightingRepository repository;

    @MockBean
    BugOrderRepository bugOrderRepository;

    @Autowired
    BugSightingService service;

    @Test
    void shouldFindAll() {
        List<BugSighting> expected = List.of(
                makeBugSighting(1),
                makeBugSighting(2),
                makeBugSighting(3)
        );

        when(repository.findAll()).thenReturn(expected);

        List<BugSighting> actual = service.findAll();

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindStickBugById() {
        BugSighting expected = makeBugSighting(6);
        when(repository.findById(6)).thenReturn(expected);

        BugSighting actual = service.findById(6);

        assertEquals(expected, actual);
    }

    @Test
    void shouldAddValidBugSighting() {
        BugSighting sighting = makeBugSighting(0);
        BugSighting expected = makeBugSighting(5);

        when(bugOrderRepository.findById(10)).thenReturn(makeBugOrder());
        when(repository.add(sighting)).thenReturn(expected);

        Result<BugSighting> actual = service.add(sighting);

        assertTrue(actual.isSuccess());
        assertEquals(expected, actual.getPayload());
    }

    @Test
    void shouldNotAddNullBugSighting() {
        Result<BugSighting> result = service.add(null);

        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("bug sighting cannot be null.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithNullBugType() {
        BugSighting sighting = makeBugSighting(0);
        sighting.setBugType(null);

        Result<BugSighting> result = service.add(sighting);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("bug type cannot be blank.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithBlankBugType() {
        BugSighting sighting = makeBugSighting(0);
        sighting.setBugType("");

        Result<BugSighting> result = service.add(sighting);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("bug type cannot be blank.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithNullBugOrder() {
        BugSighting sighting = makeBugSighting(0);
        sighting.setOrder(null);

        Result<BugSighting> result = service.add(sighting);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("bug order cannot be null.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithNonexistentBugOrder() {
        BugSighting sighting = makeBugSighting(0);
        sighting.setOrder(new BugOrder(99, "Fake", "fake"));

        Result<BugSighting> result = service.add(sighting);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("bug order id 99 not found.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithNullDescription() {
        BugSighting sighting = makeBugSighting(0);
        sighting.setDescription(null);

        Result<BugSighting> result = service.add(sighting);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("description cannot be blank.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithBlankDescription() {
        BugSighting sighting = makeBugSighting(0);
        sighting.setDescription(" ");

        Result<BugSighting> result = service.add(sighting);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("description cannot be blank.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithNullDate() {
        BugSighting sighting = makeBugSighting(0);
        sighting.setDate(null);

        Result<BugSighting> result = service.add(sighting);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("date cannot be null.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithDateOfTomorrow() {
        BugSighting sighting = makeBugSighting(0);
        sighting.setDate(LocalDate.now().plusDays(1));

        Result<BugSighting> result = service.add(sighting);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("date cannot be in the future.", result.getMessages().get(0));
    }

    @Test
    void shouldNotAddWithInterestOfZeroOrLess() {
        BugSighting sighting = makeBugSighting(0);
        sighting.setInterest(0);

        Result<BugSighting> result = service.add(sighting);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("interest must be positive.", result.getMessages().get(0));

        sighting.setInterest(-10);

        result = service.add(sighting);
        assertFalse(result.isSuccess());
        assertEquals(1, result.getMessages().size());
        assertEquals("interest must be positive.", result.getMessages().get(0));
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
        return new BugOrder(10, "Coleptera", "beetles");
    }
}