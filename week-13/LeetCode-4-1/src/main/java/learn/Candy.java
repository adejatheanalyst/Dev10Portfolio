package learn;

import java.util.ArrayList;
import java.util.List;

public class Candy {

    public static int candy(int[] ratings){
        if (ratings == null){
            return 0;
        }
        int candyCount = ratings.length;
        for(int i = 1; i < ratings.length - 1; i++){
            int current = ratings[i];
            int next = ratings[i - 1];
            if(current > next){
                candyCount++;
            }

        }
        for(int j = ratings.length - 2; j >= 0; j--) {
            int current = ratings[j];
            int next = ratings[j + 1];
            if(current > next){
                candyCount++;
            }

        }
        return candyCount;
    }



    public static void main(String[] args) {
        int[] ratings = new int[]{1,0,2};
        System.out.println(candy(ratings));
    }
}
