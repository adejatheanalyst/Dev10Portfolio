package learn;

public class addTwoNumbers {
    int val;
    addTwoNumbers next;
    addTwoNumbers(){}
    addTwoNumbers(int val){this.val = val;}
    addTwoNumbers(int val, addTwoNumbers next){this.val = val; this.next = next;}

    public static addTwoNumbers getValofList(addTwoNumbers l1, addTwoNumbers l2){
        addTwoNumbers result = new addTwoNumbers(0);
        addTwoNumbers current = result;
        int sum = 0;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum /= 10;
            current.val = sum % 10;
            current.next = new addTwoNumbers(current.val);
            current = current.next;
        }
        return result.next;

    }




    public static void main(String[] args) {
        addTwoNumbers l1 = new addTwoNumbers(2, new addTwoNumbers(4, new addTwoNumbers(3)));
        addTwoNumbers l2 = new addTwoNumbers(5, new addTwoNumbers(6, new addTwoNumbers(4)));
        getValofList(l1, l2);




    }
}
