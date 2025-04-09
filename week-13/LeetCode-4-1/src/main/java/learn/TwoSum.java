package learn;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target){
        int sum = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sum; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }

//        for (int i = 0; i < nums.length - 1; i++) {
//            if (nums[i] + nums[i + 1] == target) {
//                int[] result = {i, i + 1};
//                System.out.println(Arrays.toString(result));
//                return result;
//            }
//            if (i - 1 > 0 && nums[i] + nums[i - 1] == target) {
//                int[] result = {i, i - 1};
//                System.out.println(Arrays.toString(result));
//                return result;
//            }
//
//        }
        return null;
    }







    public static void main(String[] args) {
        int[] nums = {3,2,3};
        int target = 6;
        twoSum(nums, target);
    }

}
