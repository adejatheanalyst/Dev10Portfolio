public class Exercise09 {

    public static void main(String[] args) {
        String[] haystack = makeHaystack();

        // A needle is randomly placed in a haystack array with a capacity of 100.
        // 1. Loop through the haystack and find the needle.
        for (int i = 0; i < haystack.length; i++){
            if(haystack[i] != null && "needle".equals(haystack[i])){
//           if (haystack[i]  != null){                                         //if("needle".equals(haystack[i])){
                // 2. Print the index where you found it.
                System.out.println("Needle found at index: " + i);
                break;
            }
        }

        // 2. Print the index where you found it.
        // Hint: this is an exercise about the default value of strings.
    }

    public static String[] makeHaystack() {
        String[] haystack = new String[100];
        int index = (int) (Math.random() * haystack.length);
        haystack[index] = "needle";
        return haystack;
    }
}
