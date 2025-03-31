public class DiscountCalculator {
    public static void main(String[] args) {
        // Create in for customer Age
        int customerAge = 18;
        boolean qualifiesForDiscount;
        qualifiesForDiscount = customerAge <=18 || customerAge >=65;
System.out.println("Your Age: " + customerAge + ".Do you qualify for a discount? " + qualifiesForDiscount);
        customerAge = 65;
        qualifiesForDiscount = customerAge <=18 || customerAge >=65;
        System.out.println("Your Age: " + customerAge +". Do you qualify for a discount? " + qualifiesForDiscount);
        customerAge = 27;
        qualifiesForDiscount = customerAge <=18 || customerAge >=65;
        System.out.println("Your Age: " + customerAge + ". Do you qualify for a discount? " + qualifiesForDiscount);
        customerAge = 10;
        qualifiesForDiscount = customerAge <=18 || customerAge >=65;
        System.out.println("Your Age: " + customerAge + ". Do you qualify for a discount? " + qualifiesForDiscount);




    }
}
