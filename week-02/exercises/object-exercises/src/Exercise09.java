public class Exercise09 {

    // 1. Create a new method in the Balloon class.
    // Name: isExploded
    // Inputs: none
    // Output: boolean
    // Description: if the psi field is greater than 16.0, returns true.
    // Otherwise, returns false.

    // 2. Edit the getPsi method.
    // If the psi field is greater than 16.0, return Double.POSITIVE_INFINITY.
    // Otherwise, return psi.

    public static void main(String[] args) {

        // 3. Uncomment the code below.
        // 4. Fix any errors by editing the Balloon class.
        // 5. Confirm the output is similar to Sample Output

        Balloon b = new Balloon("orange");
        for (int i = 0; i < 10; i++) {
            b.inflate();
            System.out.printf("psi:%s, exploded?:%s%n", b.getPsi(), b.isExploded() ? "yes" : "no");
        }

//        Sample Output
//        psi:3.168552425925846, exploded?:no
//        psi:6.449317768787848, exploded?:no
//        psi:9.672181651750737, exploded?:no
//        psi:13.352370783778934, exploded?:no
//        psi:Infinity, exploded?:yes
//        psi:Infinity, exploded?:yes
//        psi:Infinity, exploded?:yes
//        psi:Infinity, exploded?:yes
//        psi:Infinity, exploded?:yes
//        psi:Infinity, exploded?:yes
    }
}
