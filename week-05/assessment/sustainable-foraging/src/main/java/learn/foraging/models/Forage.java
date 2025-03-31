package learn.foraging.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Forage {

    private int id;
    private LocalDate date;
    private Forager forager;
    private Item item;
    private BigDecimal kilograms;

    public Forage() {
    }

    public Forage(int id, LocalDate date, Forager forager, Item item, BigDecimal kilograms) {
        this.id = id;
        this.date = date;
        this.forager = forager;
        this.item = item;
        this.kilograms = kilograms;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Forager getForager() {
        return forager;
    }

    public void setForager(Forager forager) {
        this.forager = forager;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public BigDecimal getKilograms() {
        return kilograms;
    }

    public void setKilograms(BigDecimal kilograms) {
        this.kilograms = kilograms;
    }

    public BigDecimal getValue() {
        if (item == null || item.getDollarPerKilogram() == null) {
            return BigDecimal.ZERO;
        }
        return item.getDollarPerKilogram().multiply(kilograms);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Forage forage = (Forage) o;

        if (id != forage.id) return false;
        if (!Objects.equals(date, forage.date)) return false;
        if (!Objects.equals(forager, forage.forager)) return false;
        if (!Objects.equals(item, forage.item)) return false;
        return Objects.equals(kilograms, forage.kilograms);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (forager != null ? forager.hashCode() : 0);
        result = 31 * result + (item != null ? item.hashCode() : 0);
        result = 31 * result + (kilograms != null ? kilograms.hashCode() : 0);
        return result;
    }
}
