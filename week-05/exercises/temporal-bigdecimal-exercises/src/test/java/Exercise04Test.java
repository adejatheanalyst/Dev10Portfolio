import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class Exercise04Test {

    Exercise04 instance = new Exercise04();

    @Test
    void add() {

        String[][] testCases = {
                {"5.55", "-22", "34.567"},
                {"1", "0", "1.33333"},
                {"0", "0", "0"},
                {"999999999999", "999999999999", "999999999999"},
                {"0.99999999999999", "-0.03", "34.567"}
        };

        for (String[] args : testCases) {
            BigDecimal a = new BigDecimal(args[0]);
            BigDecimal b = new BigDecimal(args[1]);
            BigDecimal c = new BigDecimal(args[2]);
            assertEquals(a.add(b).add(c), instance.add(a, b, c));
        }
    }

    @Test
    void divideWithTwoDecimalPlaces() {

        String[][] testCases = {
                {"100", "10"},
                {"2.0", "3.0"},
                {"999999999999", "3.333333"},
                {"87388", "5.0"},
                {"-4.35333", "0.02"}
        };

        for (String[] args : testCases) {
            BigDecimal a = new BigDecimal(args[0]);
            BigDecimal b = new BigDecimal(args[1]);
            assertEquals(a.divide(b, 2, RoundingMode.HALF_UP), instance.divideWithTwoDecimalPlaces(a, b));
        }
    }

    @Test
    void sum() {

        BigDecimal[] values = {
                new BigDecimal("2.2"),
                new BigDecimal("3.3"),
                new BigDecimal("4.4")
        };
        assertEquals(new BigDecimal("9.9000"), instance.sum(values));

        values = new BigDecimal[]{
                new BigDecimal("22.2"),
                new BigDecimal("3.3"),
                new BigDecimal("-4.4454"),
                new BigDecimal("493.4"),
                new BigDecimal("-999999.4")
        };
        assertEquals(new BigDecimal("-999484.9454"), instance.sum(values));
    }

    @Test
    void average() {

        BigDecimal[] values = {
                new BigDecimal("2.2"),
                new BigDecimal("3.3"),
                new BigDecimal("4.4")
        };
        assertEquals(new BigDecimal("3.3000"), instance.average(values));

        values = new BigDecimal[]{
                new BigDecimal("22.2"),
                new BigDecimal("3.3"),
                new BigDecimal("-4.4454"),
                new BigDecimal("493.4"),
                new BigDecimal("-999999.4")
        };
        assertEquals(new BigDecimal("-199896.9891"), instance.average(values));
    }

    @Test
    void calculateInterest() {
        BigDecimal i = new BigDecimal("100.00");
        BigDecimal r = new BigDecimal("0.05");
        int period = 10;
        assertEquals(calcInterestRefImpl(i, r, period), instance.calculateInterest(i, r, period));

        i = new BigDecimal("100.00");
        r = new BigDecimal("0.005");
        period = 100;
        assertEquals(calcInterestRefImpl(i, r, period), instance.calculateInterest(i, r, period));

        i = new BigDecimal("100.00");
        r = new BigDecimal("0.0001");
        period = 1000;
        assertEquals(calcInterestRefImpl(i, r, period), instance.calculateInterest(i, r, period));
        System.out.println(calcInterestRefImpl(i, r, period));
    }

    BigDecimal calcInterestRefImpl(BigDecimal investment, BigDecimal interestRate, int periods) {

        BigDecimal balance = investment;
        BigDecimal onePlusRate = interestRate.add(BigDecimal.ONE);
        for (int i = 0; i < periods; i++) {
            balance = balance.multiply(onePlusRate);
        }
        return balance.subtract(investment);
    }
}