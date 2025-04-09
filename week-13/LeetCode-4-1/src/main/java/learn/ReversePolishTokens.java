package learn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ReversePolishTokens {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        for (String token : tokens) {
            if (token.equals("+")) {
                stack.push(stack.pop() + stack.pop());
            } else if (token.equals("*")) {
                stack.push(stack.pop() * stack.pop());
            } else if (token.equals("/")) {
                int divisor = stack.pop();
                stack.push(stack.pop() / divisor);
            } else if (token.equals("-")) {
                int subtrahend = stack.pop();
                stack.push(stack.pop() - subtrahend);
            }
            else {
                stack.push(Integer.parseInt(token));
            }
        }
        System.out.println(Integer.parseInt(stack.pop().toString()));
    }
}
