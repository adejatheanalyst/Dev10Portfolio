import java.math.BigDecimal;

public class Exercise04 {

    // BigDecimal
    // ========================
    // Complete the numbered tasks below.
    // Open and execute the accompanying tests to confirm your solution is correct.

    // 1. add a, b, and c together and return the result
    BigDecimal add(BigDecimal a, BigDecimal b, BigDecimal c) {
        BigDecimal sum = a.add(b).add(c);
        return sum;
    }

    // 2. divide a by b and return the result with only two decimal points
    BigDecimal divideWithTwoDecimalPlaces(BigDecimal a, BigDecimal b) {
        BigDecimal result = a.divide(b, 2, BigDecimal.ROUND_HALF_UP);
        return result;
    }

    // 3. calculate the sum of elements in values
    // and return it with a scale of 4.
    BigDecimal sum(BigDecimal[] values) {
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal value : values) {
            sum = sum.add(value);
        }
        return sum.setScale(4, BigDecimal.ROUND_HALF_UP);
    }

    // 4. calculate the average of elements in values
    BigDecimal average(BigDecimal[] values) {
        BigDecimal average = sum(values).divide(new BigDecimal(values.length), 4, BigDecimal.ROUND_HALF_UP);
        return average;
    }

    /**
     * 5. complete the calculateInterest method using the spec below.
     * <p>
     * Calculates the total interest earned on an investment.
     * Does *not* calculate the final balance, just the interest over and above the initial investment.
     *
     * @param investment   the starting balance
     * @param interestRate the interest rate expressed
     * @param periods      number of periods in which to apply the interest
     * @return total interest earned (final balance - initial investment)
     */
    BigDecimal calculateInterest(BigDecimal investment, BigDecimal interestRate, int periods) {
        BigDecimal balance = investment;
        BigDecimal onePlusRate = interestRate.add(BigDecimal.ONE);
        for (int i = 0; i < periods; i++) {
            balance = balance.multiply(onePlusRate);
        }
        return balance.subtract(investment);
    }
}
