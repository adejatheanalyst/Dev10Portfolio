package learn.foraging;

import learn.foraging.models.Category;
import learn.foraging.models.Forager;
import learn.foraging.models.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class TestData {
    public static final Item EDIBLE = new Item(1, "EDIBLE", Category.EDIBLE, BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP));
    public static final Item MEDICINAL = new Item(2, "MEDICINAL", Category.MEDICINAL, BigDecimal.TEN.setScale(2, RoundingMode.HALF_UP));
    public static final Item INEDIBLE = new Item(3, "INEDIBLE", Category.INEDIBLE, BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));
    public static final Item POISONOUS = new Item(4, "POISONOUS", Category.POISONOUS, BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP));

    public static final Forager FORAGER_ONE = new Forager(1, "First 1", "Last 1", "OR");
    public static final Forager FORAGER_TWO = new Forager(2, "First 2", "Last 2", "TX");

    public static final LocalDate JAN_01_2023 = LocalDate.of(2023, 1, 1);
    public static final BigDecimal ONE_KILO = new BigDecimal("1.00");
}
