package learn;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode makeTreeNode(int[] numbers) {
        if (numbers == null || numbers.length == 0) return null;
        TreeNode[] result = new TreeNode[numbers.length];
        for (int j = 0; j < numbers.length; j++) {
            result[j] = new TreeNode(numbers[j]);
        }
        for (int i = 0; i < numbers.length; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < numbers.length && right < numbers.length) {
                int leftVal = numbers[left];
                int rightVal = numbers[right];
                if (leftVal < rightVal) {
                    result[i].left = result[left];
                    result[i].right = result[right];
                } else { //
                    result[i].left = result[right];
                    result[i].right = result[left];
                }
            } else if (left < numbers.length) {
                result[i].left = result[left];
            } else if (right < numbers.length) {
                result[i].right = result[right];
            }
        }
        return result[0];
    }

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        // Base case: if both trees are null, they are identical
        if (p == null && q == null) {
            return true;
        }
        // If only one tree is null or the values are different, they are not identical
        if (p == null || q == null || p.val != q.val) {
            return false;
        }
        // Recursively check if the left and right subtrees are identical
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

//        TreeNode root1 = p;
//        TreeNode root2 = q;
//        int rootMatch = 0; // track matches.
//        int leftMatch = 0;
//        int rightMatch = 0;
//
//        while (true) {
//            if (root1 == null && root2 == null || root1.val == root2.val) {
//                rootMatch++;
//                if (root1.left != null && root2.left != null) {
//                    if (root1.left.val == root2.left.val) {
//                        leftMatch++;
//                    }
//                    if (root1.right != null && root2.right != null) {
//                        if (root1.right.val == root2.right.val) {
//                            rightMatch++;
//                            break;
//                        }
//                    }
//                }
//            }break;
//        }
//        if (rootMatch >= 1 && leftMatch >= 1 && rightMatch >= 1) {
//            return true;
//        }
//        return false;
    public static int maxDepth(TreeNode root){
            if (root == null){
                return 0;
            }
            int leftDepth =  maxDepth(root.left);
            int rightDepth = maxDepth(root.right);

            return Math.max(leftDepth, rightDepth) + 1;
        }
//        TreeNode dummy = root;
//        int depthCount = 1;
//        while(dummy != null){
//            if(dummy.left != null && dummy.right != null){
//                depthCount++;
//                dummy = dummy.right;
//            }
//            if(dummy.right == null && dummy.left == null){
//                break;
//            }
//
//        }
//        return depthCount:
    public static TreeNode invertTree(TreeNode root){
        if(root == null){
            return null;
        }
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
         invertTree(root.left);
         invertTree(root.right);
        return root;
    }



  public static void main(String[] args) {
      TreeNode p = makeTreeNode(new int[]{10,5,15});
      TreeNode q = makeTreeNode(new int[]{10, 5, 0, 0, 15});
      TreeNode root = makeTreeNode(new int[]{4,2,7,1,3,6,9});
      System.out.println(isSameTree(p,q));
      System.out.println(invertTree(root));
      System.out.println(maxDepth(p));
//          TreeNode = new

  }
}
