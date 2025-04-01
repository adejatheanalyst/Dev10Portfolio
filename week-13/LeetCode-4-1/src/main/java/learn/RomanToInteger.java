package learn;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public static void main(String[] args) {


        Map<Character , Integer> roman = new HashMap<>();
        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);
        String s = "MCDLXXVI";
        int result = 0;
        for (int i = 0; i < s.length(); i++){
            int current = roman.get(s.charAt(i));
            if(i + 1 < s.length() && current < roman.get(s.charAt(i + 1))){
                result -= current;
            }else{
                result += current;
            }
        }
        System.out.println(result);


//        int I = 1;
//        int V = 5;
//        int X = 10;
//        int L = 50;
//        int C = 100;
//        int D = 500;
//        int M = 1000;
//
//        String s = "MCDLXXVI";
//        int result = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == 'I') {
//                result += I;
//                if(i + 1 < s.length() && s.charAt(i + 1) == 'V'){
//                    result += 3;
//                } else if(i + 1 < s.length() && s.charAt(i + 1) == 'X'){
//                    result += 8;
//                }
//            } else if (s.charAt(i) == 'V') {
//                if (s.charAt(i - 1) == 'I') {
//                    continue;
//                }
//                result += V;
//            } else if (s.charAt(i) == 'X' ) {
//                if (s.charAt(i - 1) == 'I') {
//                    continue;
//                }
//                result += X;
//                if(i + 1 < s.length() && s.charAt(i + 1) == 'L'){
//                    if (s.charAt(i - 1) == 'X') {
//                        continue;
//                    }
//                    result += 30;
//                } else if(i + 1 < s.length() && s.charAt(i + 1) == 'C'){
//                    result += 80;
//                }
//            } else if (s.charAt(i) == 'L') {
//                if(s.charAt(i - 1) == 'X'){
//                    continue;
//                }
//                result += L;
//            } else if (s.charAt(i) == 'C') {
//                if(s.charAt(i - 1) == 'X'){
//                    continue;
//                }
//                result += C;
//                if(i + 1 < s.length() && s.charAt(i + 1) == 'D'){
//                    if(s.charAt(i - 1) == 'C'){
//                        continue;
//                    }
//                    result += 300;
//                } else if(i + 1 < s.length() && s.charAt(i + 1) == 'M'){
//                    result += 800;
//                }
//            } else if (s.charAt(i) == 'D') {
//                if(s.charAt(i - 1) == 'C'){
//                    continue;
//                }
//                result += D;
//            } else if (s.charAt(i) == 'M') {
//                if(i-1 >= 0 && s.charAt(i-1) == 'C'){
//                    continue;
//                }
//                result += M;
//            }
//            else {
//                break;
//            }
//
//
//        }
//        System.out.println(result);


    }
}
