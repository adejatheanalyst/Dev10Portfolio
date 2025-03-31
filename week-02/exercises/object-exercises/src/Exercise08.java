public class Exercise08 {

    // 1. Create a new method in the Balloon class.
    // Name: inflate
    // Inputs: none
    // Output: void
    // Description: adds a random value to the psi field between 0.0 and 5.0
    // this.psi = this.psi + Math.random() * 5.0;

    public static void main(String[] args) {

        // 2. Uncomment the code below.
        // 3. Fix any errors by editing the Balloon class.
        // 4. Confirm the output is similar to Sample Output

        Balloon b = new Balloon("green");
        for (int i = 0; i < 10; i++) {
            b.inflate();
            System.out.println("psi:" + b.getPsi());
        }

//        Sample Output (expected varies because of randomness)
//        psi:4.527504731304849
//        psi:7.280724276108291
//        psi:11.888687602911638
//        psi:12.494627986679937
//        psi:14.363178677406783
//        psi:18.86321275348291
//        psi:19.365959293933678
//        psi:20.576153363408363
//        psi:22.066644549632244
//        psi:22.15619546631708
    }
}
