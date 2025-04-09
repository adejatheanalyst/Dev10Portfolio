package learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MergedArrays {
    int val;
    MergedArrays next;

    MergedArrays() {
    }

    MergedArrays(int val) {
        this.val = val;
    }

    MergedArrays(int val, MergedArrays next) {
        this.val = val;
        this.next = next;
    }


    public static MergedArrays mergeTwoArrays( MergedArrays l1, MergedArrays l2) {
        MergedArrays newArray = new MergedArrays();
        MergedArrays result = newArray;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                newArray.next = l1;
                l1 = l1.next;
            } else {
                newArray.next = l2;
                l2 = l2.next;
            }
            newArray = newArray.next;
        }

        if (l1 != null) {
            newArray.next = l1;
        } else {
            newArray.next = l2;
        }

        System.out.println(result.next);
        return result.next;
        }


    public static void main(String[] args) {
        MergedArrays list1 = new MergedArrays(1, new MergedArrays(2, new MergedArrays(4)));
        MergedArrays list2 = new MergedArrays(1, new MergedArrays(3, new MergedArrays(4)));
        mergeTwoArrays(list1, list2);
    }
}
