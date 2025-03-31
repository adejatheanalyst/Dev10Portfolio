package learn.commerce;

import java.util.Arrays;
import java.util.List;

/**
 * An informal sale contract for purchasable products and services.
 * Products and services are modeled as LineItems.
 * They're added to the order one at a time with the `add` method.
 */
public class Order {

    private int orderId;
    private LineItem[] lineItems = new LineItem[0];

    public Order(int orderId) {
        this.orderId = orderId;
    }


    public Order(){
    }

    public int getOrderId() {
        return orderId;
    }

    public LineItem[] getLineItems() {
        return lineItems;
    }

    public double getTotal() {
        // 1. Complete the getTotal method.
        // It should calculate the order's grand total by summing totals from each LineItem.
        double total = 0.0;
        for (LineItem item : lineItems){
            total += item.getQuantity() * item.getPrice();
        }
        return total;
    }

    public boolean add(LineItem lineItem) {

        // invalid item
        if (lineItem == null || lineItem.getQuantity() <= 0 || lineItem.getPrice() < 0.0) {
            return false;
        }

        // Extend the array by one and add the lineItem to the end.
        lineItems = Arrays.copyOf(lineItems, lineItems.length + 1);
        lineItems[lineItems.length - 1] = lineItem;

        return true;
    }


}

