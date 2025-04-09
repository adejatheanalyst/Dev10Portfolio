package learn;

public class subsetXORSum {

    public static int subsetXORsum(int [] nums){
        int sum = 0;
        for(int num : nums){
            sum |= num;
        }
        return sum *(1 << (nums.length -1));
    }
    public static int maximumCount(int[] nums){
        int neg = 0;
        int pos = 0;

        for(int num : nums){
            if(num > 0){
                pos++;
            }else if(num < 0) {
                neg++;
            }
        }
        if(neg > pos){
            return neg;
        }else {
            return pos;
        }

    }


    public static void main(String[] args) {
        int[] nums = new int[]{5,1,6};
        int[] nums2 = new int[]{5,20,66,1314};
        System.out.println(maximumCount(nums2));
        System.out.println(subsetXORsum(nums));
    }
}
