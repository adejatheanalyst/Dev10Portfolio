package learn;

import java.math.BigInteger;

public class plusOne {
    public static int[] plusOne(int[] digits){
        if (digits == null || digits.length == 0) return null;

        int[] dummy = new int[digits.length];
        StringBuilder number = new StringBuilder(digits.length);
        for (int i = 0; i < dummy.length; i++) {
                dummy[i] = digits[i];
                number.append(digits[i]);
            }
        BigInteger toAdd = new BigInteger(number.toString());
        BigInteger plus1 = BigInteger.valueOf(1);
        BigInteger math = toAdd.add(plus1);
        String toString = String.valueOf(math);

        int[] result = new int[toString.length()];
        for(int i = 0; i < toString.length(); i++){
            int digit = Character.getNumericValue(toString.charAt(i));
            result[i] = digit;
        }


        return result;
    }



    public static void main(String[] args) {
        int[] numbers = new int[]{9,8,7,6,5,4,3,2,1,0};
        System.out.println(plusOne(numbers));
    }
}
