package learn.commerce;

import java.util.Objects;

/**
 * An product or service that's part of an Order.
 * Could represent anything with a price and quantity.
 * Examples:
 * new LineItem("Grass Seed", 25.49, 1)
 * new LineItem("Double Scoop Cone - Rocky Road", 5.45, 2)
 * new LineItem("Technician Service (hours)", 56.75, 8.50)
 * new LineItem("Movie Rental - The Muppet Movie", 1.99, 1)
 */
public class LineItem {

    private final String itemName;
    private final double price;
    private final double quantity;

    public LineItem(String itemName, double price, double quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getTotal() {
        return price * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return Double.compare(lineItem.price, price) == 0 &&
                Double.compare(lineItem.quantity, quantity) == 0 &&
                Objects.equals(itemName, lineItem.itemName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, price, quantity);
    }
}
