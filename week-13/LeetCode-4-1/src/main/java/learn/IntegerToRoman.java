package learn;

import java.util.HashMap;
import java.util.Map;

public class IntegerToRoman {
    public static void main(String[] args) {
        Map<Integer, Character> roman = new HashMap<>();
        roman.put(1, 'I');
        roman.put(5, 'V');
        roman.put(10, 'X');
        roman.put(50, 'L');
        roman.put(100, 'C');
        roman.put(500, 'D');
        roman.put(1000, 'M');
        int num = 3749;
        StringBuilder result = new StringBuilder();
        int divisor = 1000;
        while (num > 0) {
            int digit = num / divisor;
            if (digit > 0) {
                if (digit == 9) {
                    result.append(roman.get(divisor));
                    result.append(roman.get(divisor * 10));
                } else if (digit >= 5) {
                    result.append(roman.get(divisor * 5));
                    for (int i = 0; i < digit - 5; i++) {
                        result.append(roman.get(divisor));
                    }
                } else if (digit == 4) {
                    result.append(roman.get(divisor));
                    result.append(roman.get(divisor * 5));
                } else {
                    for (int i = 0; i < digit; i++) {
                        result.append(roman.get(divisor));
                    }
                }
            }
            num = num % divisor;
            divisor /= 10;
        }
        System.out.println(result.toString());
    }
}
