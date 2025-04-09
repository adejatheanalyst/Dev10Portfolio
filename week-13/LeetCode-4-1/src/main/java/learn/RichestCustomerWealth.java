package learn;

public class RichestCustomerWealth {
    public static void main(String[] args) {
        int[][] accounts = {{1, 2, 3}, {3, 2, 1}};
        int result = 0;
        for(int [] c : accounts){ // for each loop
            int currentCustomerWealth = 0; // define customer wealth variable starting at 0
            for(int value : c){ // loop through each value in the customer account
                currentCustomerWealth += value; // add that value to customer wealth
            }

            result = Math.max(result,currentCustomerWealth);

        }
        System.out.println(result);


    }
}
