package learn;

import java.util.ArrayList;
import java.util.List;

public class HouseRobber {

    public static int rob(int[] nums){
        int n = nums.length;
        if(n == 1) return nums[0];
        int housebefore = 0;
        int twoHousesBefore = 0;

        for(int i = 0; i < nums.length; i++){
            int temp = housebefore;
            housebefore = Math.max(twoHousesBefore + nums[i], housebefore);
            twoHousesBefore = temp;
        }




        return housebefore;
    }



    public static void main(String[] args) {
        int [] nums = new int[]{2,7,9,3,1};
        System.out.println(rob(nums));
    }
}
