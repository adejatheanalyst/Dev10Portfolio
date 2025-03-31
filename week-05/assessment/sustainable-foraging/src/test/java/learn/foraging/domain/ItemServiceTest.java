package learn.foraging.domain;

import learn.foraging.data.ForageRepository;
import learn.foraging.data.ItemRepository;
import learn.foraging.data.ItemRepositoryDouble;
import learn.foraging.models.Category;
import learn.foraging.models.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static learn.foraging.TestData.EDIBLE;
import static learn.foraging.TestHelper.makeResult;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ItemServiceTest {
    @Autowired
    ItemService service;
    @MockBean
    ItemRepository repository;

//    ItemService service = new ItemService(new ItemRepositoryDouble());

    @Test
    void shouldNotAddNullItem() {
        Result<Item> expected = makeResult("Item must not be null.", null);
        expected.setResultType(ResultType.INVALID);
        Result<Item> actual = service.add(null);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullName() {
        Item arg = new Item(0, null, Category.EDIBLE, new BigDecimal("5.00"));
        Result<Item> expected = makeResult("Item name is required.", null);
        expected.setResultType(ResultType.INVALID);
        Result<Item> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddBlankName() {
        Item arg = new Item(0, "   \t\n", Category.EDIBLE, new BigDecimal("5.00"));
        Result<Item> expected = makeResult("Item name is required.", null);
        expected.setResultType(ResultType.INVALID);
        Result<Item> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNullDollars() {
        Item arg = new Item(0, "Test Item", Category.EDIBLE, null);
        Result<Item> expected = makeResult("$/Kg is required.", null);
        expected.setResultType(ResultType.INVALID);
        Result<Item> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddNegativeDollars() {
        Item arg = new Item(0, "Test Item", Category.EDIBLE, new BigDecimal("-5.00"));
        Result<Item> expected = makeResult("$/Kg must be between 0.00 and 7500.00.", null);
        expected.setResultType(ResultType.INVALID);
        Result<Item> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotAddTooLargeDollars() {
        Item arg = new Item(0, "Test Item", Category.EDIBLE, new BigDecimal("9999.00"));
        Result<Item> expected = makeResult("$/Kg must be between 0.00 and 7500.00.", null);
        expected.setResultType(ResultType.INVALID);
        expected.setResultType(ResultType.INVALID);
        Result<Item> actual = service.add(arg);
        assertEquals(expected, actual);
    }

    @Test
    void shouldAdd() {
        Item arg = new Item(0, "Test Item", Category.EDIBLE, new BigDecimal("5.00"));
        Result<Item> expected = makeResult(null, EDIBLE);
        expected.setResultType(ResultType.SUCCESS);
        Result<Item> actual = service.add(arg);
        assertEquals(expected.getResultType(), actual.getResultType());
    }

}