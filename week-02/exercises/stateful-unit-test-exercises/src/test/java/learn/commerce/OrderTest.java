package learn.commerce;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Order order = new Order(25);

    @Test
    void shouldHaveOrderId() {
        assertEquals(25, order.getOrderId());
    }

    @Test
    void shouldAddValidItems() {
        LineItem grassSeed = new LineItem("Grass Seed", 19.99, 2);
        boolean result = order.add(grassSeed);
        assertTrue(result);
        assertEquals(1, order.getLineItems().length);
        assertEquals(grassSeed, order.getLineItems()[0]);

        LineItem gardenRake = new LineItem("Garden Rake", 44.99, 1);
        result = order.add(gardenRake);
        assertTrue(result);
        assertEquals(2, order.getLineItems().length);
        assertEquals(gardenRake, order.getLineItems()[1]);

        LineItem hose = new LineItem("Garden Hose - 50ft", 38.49, 1);
        result = order.add(hose);
        assertTrue(result);
        assertEquals(3, order.getLineItems().length);
        assertEquals(hose, order.getLineItems()[2]);
    }

    // 1. Add test shouldNotAddInvalidItems: confirm that it's not possible to add items with <= 0 quantity or < 0 price.
    @Test
    void shouldNotAddInvalidItems() {
        LineItem dixieCup = new LineItem("Dixie Cup", -.30, -2);
        boolean result = order.add(dixieCup);
        assertFalse(result);
        assertEquals(0, order.getLineItems().length);


    }

    // 2. Test the order.getTotal() in various scenarios and confirm it's correct.
    @Test
    void shouldCalculateTotalCorrectly() {
        // make a few items
        LineItem item1 = new LineItem("Item 1", 10.0, 2);
        LineItem item2 = new LineItem("Item 2", 5.0, 3);
        LineItem item3 = new LineItem("Item 3", 7.5, 4);

        // Add the items to the order
        order.add(item1);
        order.add(item2);
        order.add(item3);

        // Calculate the expected total
        double expectedTotal = (2 * 10.0) + (3 * 5.0) + (4 * 7.5);

        // Verify that the order's total is correct
        assertEquals(expectedTotal, order.getTotal(), 0.001);

        // Test empty order
        Order emptyOrder = new Order();
        assertEquals(0.0, emptyOrder.getTotal(), 0.001);

        // Test with a single item
        Order singleItemOrder = new Order();
        LineItem singleItem = new LineItem("Single Item", 15.0, 3);  // 3 * 15.0 = 45.0
        singleItemOrder.add(singleItem);
        assertEquals(45.0, singleItemOrder.getTotal(), 0.001);
    }

    // 3. If you tackle `order.remove`, test the method thoroughly.

}