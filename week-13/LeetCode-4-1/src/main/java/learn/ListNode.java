package learn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
    public static ListNode removeNthNode(ListNode head, int n){
        List<Integer> newlist = new ArrayList<>();
        int placeCount = 0;
        ListNode dummy = head;
        ListNode end = dummy;

        while(end != null){
            newlist.add(end.val);
            end = end.next;
        }
        for(int i = newlist.size() - 1; i >= 0; i--){
            placeCount++;
            if (placeCount == n){
                newlist.remove(i);
                break;
            }
        }
        int[] resultList = newlist.stream().mapToInt(Integer::intValue).toArray();
        ListNode result = makeListNode(resultList);
        return result;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        ListNode dummy2 = dummy;
        ListNode result = head;
        while (result != null && result.next != null) {
            if (result.val == result.next.val){
                int duplicateVal = result.val;
                while (result != null && result.val == duplicateVal){
                    result = result.next;
            }
        } else {
                dummy.next = new ListNode(result.val);
                result = result.next;
                dummy = dummy.next;
            }
        }
        if(result != null){
            dummy.next = new ListNode(result.val);
        }
        return dummy2.next ;
    }
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode dummy = head;
        int length = 0;
//        ListNode end = dummy;
        while (dummy != null) {
            length++;
            dummy = dummy.next;
        }
        int rotation = k % length;

        for (int i = 0; i < rotation; i++) {
            ListNode end = head, prev = null;
            while (end.next != null) {
                prev = end;
                end = end.next;
            }
            end.next = head;
            prev.next = null;
            head = end;
        }
        return head;
    }
    public static ListNode reverseList(ListNode head){
        List<Integer> newlist = new ArrayList<>();
        ListNode dummy = head;
        ListNode end = dummy;
        while(end != null){
            newlist.add(end.val);
            end = end.next;
        }
        List<Integer> reversedList = new ArrayList<>(newlist.size());
        for (int i = newlist.size() - 1; i >= 0; i--) {
            reversedList.add(newlist.get(i));
        }
        ListNode listNode = new ListNode();
        ListNode result = listNode; // pointer to get head of listnodes
        for (int j : reversedList) {
            listNode.next = new ListNode(j);
            listNode = listNode.next;
        }
        return result.next;
    }

    public static ListNode makeListNode(int[] numbers) {
        List<Integer> copyList = new ArrayList<>();
        for (int i : numbers) {
            copyList.add(i);
        }
        ListNode listNode = new ListNode();
        ListNode result = listNode; // pointer to get head of listnodes
            for (int j : copyList) {
                listNode.next = new ListNode(j);
                listNode = listNode.next;
            }
            return result.next; // skip initial blank value.
    }
    public static boolean hasCycle(ListNode head){
        ListNode slow =  head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
         ListNode head = makeListNode(new int[]{3,2,0,-4});
         int pos = 1; //
         ListNode dups = makeListNode(new int[]{1,1,2,3,3,5,6,6,7});
        int n = 1;
        ListNode toReverse = makeListNode(new int[]{1,2});
//        System.out.println(removeNthNode(head, n));
//        System.out.println(deleteDuplicates(dups));
//        System.out.println(rotateRight(head, 4));
//        System.out.println(hasCycle(head));
        System.out.println(reverseList(toReverse));

    }



}

