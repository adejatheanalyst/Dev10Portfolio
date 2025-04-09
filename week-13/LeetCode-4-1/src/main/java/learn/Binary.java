package learn;
import java.math.BigInteger;
import java.util.BitSet;

public class Binary {
    public static String convertToBinary(String a){
        StringBuilder dummyResult = new StringBuilder();
        int result = Integer.parseUnsignedInt(a);
        if(result == 0){
            return "0";
        }
        while(result != 0){
            // divide result to get remainder if there is one
            dummyResult.append(result % 2 == 1 ? "1" : "0");
            result /= 2;
        }
        return dummyResult.reverse().toString();
    }
    public static double myPow(int x, int n) {
        int result = 1;

        if (n == 0) { // if n is 0, return 1
            result = 1;
        } else if (n < 0) { // if power is negative, return 1 divided by number and negative power
            n = -n;
            x = 1 / x;
            // convert into positive reciprocal
        }
        while( n != 0){
            if((n & 1) != 0){
                result *= x;
            }
            x *= x;
            n >>>=1;
        }
        return result;
    }

    public  static BigInteger convertFROMbinary(String a){
        return new BigInteger(a.trim(), 2);
//        long dummyResult = 0;
//        int power = 0;
//        a = a.trim();
////        int dummyA = Integer.parseInt(dummy);
//        for(int i = a.length() -1; i >=0; i--){
//            int digit = a.charAt(i) - '0';
////            System.out.println(digit);
//            dummyResult += digit * (1 << power);
//            power ++;
//        }
//        System.out.println(dummyResult);
//return dummyResult;
    }

    public static String addBinary(String a, String b){
        BigInteger dummyA = (convertFROMbinary(a));
        BigInteger dummyB = (convertFROMbinary(b));
//        StringBuilder dummyResult = new StringBuilder();
        BigInteger total = dummyA.add(dummyB);
        if (total.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Integer overflow occurred during addition");
        }

       String toString = String.valueOf(total);
        String result = convertToBinary(toString);

        System.out.println(result);
        return result;
    }
    public static String reverseWords(String s){
        String[] reversedString = s.split("\\s+");
        StringBuilder result = new StringBuilder();
//        String wordStart = " "; // pointer for start of word

        for (int i = reversedString.length - 1; i >= 0; i--) {
//           wordStart = String.valueOf(s.charAt(i));
//           if(wordStart == " "){
//               for(int j = 0; j < s.length(); i++){
                   result.append(reversedString[i]);
                   if(i != 0){
                       result.append(" ");
               }

            }
        return result.toString().trim();

//       String word = reversedString.substring(s.charAt(i), s.indexOf(""));
    }

//        return String.valueOf(reversedString);

    public static int reverseBits(int n){
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1;           // Shift result left by 1
            result |= (n & 1);      // Add least significant bit of n
            n >>>= 1;               // Unsigned shift n right by 1
        }
        return result;
//        String paddedBinary = String.format("%32s", n).replace(' ', '0');;
//        String reverse = new StringBuilder(paddedBinary).reverse().toString();
//       String result = String.valueOf((convertFROMbinary(reverse)));
//
//        return (int) Long.parseLong(reverse, 2);
    }
    public static String preserveLeadingZeros(String binaryString) {
        // Pad the binary string with leading zeros to match the desired bit length
        return String.format("%32s", binaryString).replace(' ', '0');

        // Convert the padded binary string to BigInteger;
    }
    public static Object hammingWeight(int n){
        String toBinary = convertToBinary(String.valueOf(n));
        int bitCount = 0;
        for(int i = toBinary.length() -1; i >=0; i--){
            int BIT = toBinary.charAt(i) - '0';
           if(BIT == 1){
               bitCount++;
           }
        }

        Integer result =  bitCount;
//        BitSet result = new BitSet(toInt);
//        result.cardinality();
//        BitSet toInt = BitSet.
//        int count;
//        for (count=0; n; count++)
//            n &= n - 1;
        return result;
    }


    public static void main(String[] args) {
        String a = "10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101";
        String b = "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011";
        int c = 0b00000010100101000001111010011100;
        String d ="27834";
        int n = 2147483645;
        String s = "The sky is blue";
        System.out.println(reverseWords(s));
//        System.out.println(addBinary(a,b));
//        convertFROMbinary(a);
//        System.out.println(convertFROMbinary("00111001011110000010100101000000"));
//        System.out.println(convertToBinary(d));
//        System.out.println(reverseBits(c));
//        System.out.println(hammingWeight(n));
    }
}
