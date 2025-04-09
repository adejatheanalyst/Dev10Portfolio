package learn;

public class mySqrt {
    public static void sqrt(int x){
        if (x < 0) {
            System.out.println("x cannot be negative");
            return;
        }
        if (x == 0 || x == 1) {
            System.out.println(x);
            return;
        }
        int start = 1;
        int end = x;
        int ans = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2; // use order of operations
            if (mid <= x / mid) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(ans);
    }




    public static void main(String[] args) {
        int x = 2147395599;
        sqrt(x);

    }
}
