import java.util.Random;

public class Exercise16 {

    public static void main(String[] args) {
        // MERGE
        int[] one = makeRandomAscendingArray();
        int[] two = makeRandomAscendingArray();

        // makeRandomAscendingArray creates a random array with a capacity between 50 and 150.
        // Its elements are guaranteed to be sorted ascending.
        // 1. Create a new int[] with capacity for all elements from `one` and `two`.
        int[] oneTwo = new int[one.length + two.length];


        // 2. "Merge" elements from `one` and `two` into the new array so that its values are sorted.


//        Create an integer index for `one`, `two`, and the result array, all starting at 0
        int oneIndex = 0;
        int twoIndex = 0;
        int resultIndex = 0;

//        Loop resultIndex from 0 to result.length - 1:
        // for (int i = 0; i < three.length; i ++)
        while (resultIndex < oneTwo.length) { // if (oneIndex
//                //if oneIndex >= one.length, there are no `one` elements remaining so use elements from two

            if (oneIndex >= one.length) {
                oneTwo[resultIndex] = two[twoIndex];
                twoIndex++;

                //if twoIndex >= two.length, there are no `two` elements remaining so use elements from one
            } else if (twoIndex >= two.length) {
                oneTwo[resultIndex] = one[oneIndex];
                oneIndex++;
            }
//               if one[oneIndex] is less than two[twoIndex], add it to the result and increment oneIndex
            else if (one[oneIndex] < two[twoIndex]) {
                oneTwo[resultIndex] = one[oneIndex];
                oneIndex++;
            }else if (two[twoIndex] < one[oneIndex]) { // if two[twoIndex] is less than one[oneIndex], add it to the result and increment twoIndex.
                oneTwo[resultIndex] = two[twoIndex];
                twoIndex++;

            } else {//determine how to settle ties
                oneTwo[resultIndex] = one[oneIndex];
                oneIndex++;
            }resultIndex++;
        }


        System.out.println("Merged Array: ");
        for (int i = 0; i < oneTwo.length; i++) {
            System.out.println(oneTwo[i] + " ");
        }
    }




         /* Pseudocode:


           if one[oneIndex] is less than two[twoIndex], add it to the result and increment oneIndex.
           if two[twoIndex] is less than one[oneIndex], add it to the result and increment twoIndex.
           determine how to settle ties
           if oneIndex >= one.length, there are no `one` elements remaining so use elements from two
           if twoIndex >= two.length, there are no `two` elements remaining so use elements from one
          */


    public static int[] makeRandomAscendingArray() {
        Random random = new Random();
        int[] result = new int[random.nextInt(100) + 50];
        int current = random.nextInt(11);
        for (int i = 0; i < result.length; i++) {
            result[i] = current;
            current += random.nextInt(4);
        }
        return result;
    }
}