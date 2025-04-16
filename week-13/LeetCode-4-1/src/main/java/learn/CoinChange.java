package learn;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class CoinChange {

    public static int coinChange(int[] coins, int amount) {
   if(amount < 1){
       return 0;
   }
        int[] dynamPro = new int[amount + 1]; // create dynamic programming array
       dynamPro[0]=0; // first value is zero

            for (int i = 1; i <= amount; i++) { // for each index for the amount
                dynamPro[i] = Integer.MAX_VALUE; // set index to max value
                for(int j : coins){ // loop through coins array
                if(j <= i && dynamPro[i - j] != Integer.MAX_VALUE){
                    dynamPro[i] = Math.min(dynamPro[i], dynamPro[i - j] + 1);
                }
                }
            }
        return dynamPro[amount] == Integer.MAX_VALUE ? - 1 : dynamPro[amount];

    }
    public static void main(String[] args) {
        int[] coins = {1};
        int amount = 0;
        System.out.println(coinChange(coins, amount));
    }
}
