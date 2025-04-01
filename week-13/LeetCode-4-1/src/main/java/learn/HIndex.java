package learn;

import java.util.Arrays;

public class HIndex {
    public static void main(String[] args) {

        //my solution
        int[] citations = {3,0,6,1,5};
        int hIndex = 0;
//loop through citations array
        for (int citation : citations) {
            int count = 0; // count of papers with citations
            for (int j = 0; j < citations.length; j++) { // loop through array again to count papers with citations
                if (citations[j] >= citation) { // if the index value of the first loop is greater than or equal to the citation in the second loop
                    count++; // add to count
                }
            } //continue to loop through array until count is greater than or equal to citation from first loop
            if (count >= citation) { //
                hIndex = citation;System.out.println(hIndex);
                return;

            }
        }

        /// 2nd solution
        Arrays.sort(citations);
        int count = 1;
        for(int i = citations.length-1 ; i >= 0 ; i-- ){
            if(citations[i] >= count){
                count++;
            }
            else {
                break;
            }
        }
//        return count-1;
        System.out.println(count - 1);



    }
}