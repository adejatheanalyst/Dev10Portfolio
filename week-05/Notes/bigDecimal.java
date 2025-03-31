import java.math.BigDecimal;
import java.math.RoundingMode;

public class bigDecimal {
    public static void main(String[] args) {
//        double taxRevenue = 3500000000000.0;
//        int dozen = 12;
//
//        BigDecimal usTaxRevenue = new BigDecimal(taxRevenue);
//        BigDecimal perPackage = new BigDecimal(dozen);
//        BigDecimal quadCopterPrice = new BigDecimal("299.99");
//
//        BigDecimal value = null;
//        try {
//            value = new BigDecimal("nefarious");
//        } catch (NumberFormatException ex) {
//            // Character n is neither a decimal digit number, decimal point, nor "e" notation exponential mark.
//            System.out.println(ex.getMessage());
       // }
//        System.out.println(usTaxRevenue);    // 3500000000000
//        System.out.println(perPackage);      // 12
//        System.out.println(quadCopterPrice); // 299.99
//        System.out.println(value);           // null
//        BigDecimal rent = new BigDecimal("1200.00");
//        BigDecimal utilities = new BigDecimal("225.00");
//
//// add the instance to the argument and return a new BigDecimal
//        BigDecimal fixedCosts = rent.add(utilities);
//        System.out.println(fixedCosts);     // 1425.00
//
//        BigDecimal reliablePayerDiscount = new BigDecimal("55.25");
//
//// subtract the argument from the instance and return a new BigDecimal
//        fixedCosts = fixedCosts.subtract(reliablePayerDiscount);
//        System.out.println(fixedCosts);     // 1369.75
//
//// multiply the argument by the instance and return a new BigDecimal
//        BigDecimal annualCosts = fixedCosts.multiply(new BigDecimal("12"));
//        System.out.println(annualCosts);    // 16437.00
//
//// divide the instance by the argument and return a new BigDecimal
//        BigDecimal quarterlyCosts = annualCosts.divide(new BigDecimal("4"));
//        System.out.println(quarterlyCosts); // 4109.25


//        Example	Scale
//        123	0
//        12.5	1
//        12.99	2
//       -23.546 3
        //only works if all digits after the scale cut off are 0
       // BigDecimal value = new BigDecimal("99.990000");
       // System.out.println(value);             // 99.990000
       // System.out.println(value.setScale(2)); // 99.99
//rounding

//        BigDecimal value = new BigDecimal("99.990001");
//        System.out.println(value);                                   // 99.990001
//        System.out.println(value.setScale(2, RoundingMode.HALF_UP)); // 99.99
//        System.out.println(value.setScale(1, RoundingMode.HALF_UP)); // 100.0
//        System.out.println(value.setScale(0, RoundingMode.HALF_UP)); // 100



//        BigDecimal value = new BigDecimal("1.13");
//        BigDecimal twoHundredths = new BigDecimal("0.02");
//        BigDecimal threshold = new BigDecimal("2.0");
//        System.out.println("VALUE |  UP  | DOWN | CEILING | FLOOR | HALF_UP | HALF_DOWN | HALF_EVEN");
//        for (; value.compareTo(threshold) < 0; value = value.add(twoHundredths)) {
//            System.out.printf("%s  |  %s | %s  | %s     | %s   | %s     | %s       | %s%n",
//                    value,
//                    value.setScale(1, RoundingMode.UP),
//                    value.setScale(1, RoundingMode.DOWN),
//                    value.setScale(1, RoundingMode.CEILING),
//                    value.setScale(1, RoundingMode.FLOOR),
//                    value.setScale(1, RoundingMode.HALF_UP),
//                    value.setScale(1, RoundingMode.HALF_DOWN),
//                    value.setScale(1, RoundingMode.HALF_EVEN)
//            );
//        }

//        BigDecimal three = new BigDecimal("3");
//        BigDecimal oneThird = BigDecimal.ONE.divide(three, 50, RoundingMode.HALF_EVEN);
//        System.out.println(oneThird);
//
//        oneThird = BigDecimal.ONE.divide(three, 9, RoundingMode.HALF_UP);
//        System.out.println(oneThird);
//
//        oneThird = BigDecimal.ONE.divide(three, 3, RoundingMode.DOWN);
//        System.out.println(oneThird);
        BigDecimal taxRevenue = new BigDecimal("3500000000000");
        BigDecimal penny = new BigDecimal("0.01");
        for (int i = 0; i < 300000000; i++) {
            taxRevenue = taxRevenue.add(penny);
        }
        System.out.println(taxRevenue); // 3500003000000.00


        final BigDecimal dollar = BigDecimal.ONE;
        final BigDecimal quarter = new BigDecimal("0.25");
        final BigDecimal dime = new BigDecimal("0.1");
        final BigDecimal nickel = new BigDecimal("0.05");

        BigDecimal change = new BigDecimal("13.93");
        BigDecimal[] result = change.divideAndRemainder(dollar);
        System.out.println("Dollars: " + result[0].setScale(0, RoundingMode.UNNECESSARY));

        result = result[1].divideAndRemainder(quarter);
        System.out.println("Quarters: " + result[0].setScale(0, RoundingMode.UNNECESSARY));

        result = result[1].divideAndRemainder(dime);
        System.out.println("Dimes: " + result[0].setScale(0, RoundingMode.UNNECESSARY));

        result = result[1].divideAndRemainder(nickel);
        System.out.println("Nickels: " + result[0].setScale(0, RoundingMode.UNNECESSARY));

// multiply by 100 to get whole pennies
// rounding may be necessary if change has fractions of a penny
        BigDecimal pennies = result[1].multiply(new BigDecimal("100")).setScale(0, RoundingMode.HALF_EVEN);
        System.out.println("Pennies: " + pennies);

    }
}
