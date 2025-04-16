package learn;

import java.math.BigInteger;
import java.util.*;

public class TreeNode {
    Integer val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(Integer val) {
        this.val = val;
    }

    TreeNode(Integer val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode makeTreeNode(Integer[] numbers) {
        if (numbers == null || numbers.length == 0) return null;

        TreeNode[] nodes = new TreeNode[numbers.length];

        // Step 1: Create nodes (nulls stay null)
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != null) {
                nodes[i] = new TreeNode(numbers[i]);
            }
        }

        // Step 2: Link children
        for (int i = 0; i < numbers.length; i++) {
            TreeNode current = nodes[i];
            if (current == null) continue;

            int leftIndex = 2 * i + 1;
            int rightIndex = 2 * i + 2;

            if (leftIndex < numbers.length) {
                current.left = nodes[leftIndex];
            }
            if (rightIndex < numbers.length) {
                current.right = nodes[rightIndex];
            }
        }

        return nodes[0];
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
        // use recursion to check if
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    public static boolean isSymmetric(TreeNode rootleft, TreeNode rootRight){ // pass in both tree nodes to check if sym
        if (rootleft == null && rootRight == null) { // if root is null return true
            return true;
        }

        if(rootleft == null || rootRight == null){ //if roots are not a match  return false
            return false;
        }
        if(rootRight.val != rootleft.val){ // if roots values do not match return false
            return false;
        }
// use recursion and call method on root left node and root right node respectively.
        return isSymmetric(rootleft.left, rootRight.right) && isSymmetric(rootleft.right, rootRight.left);


//        if(root.left == null || root.right == null){
//            if(root.left == null && root.right == null){
//                isSymmetric =  true;
//            }
//            if(root.left.val == null || root.right.val == null){
//                isSymmetric = false;
//                if(root.left.val != root.right.val){
//                    isSymmetric = false;
//                }
//            }
//        if(root.left.left == null || root.right.right == null ){
//
//        }
//            isSymmetric = true;
//            }
//        if(root.left.left.val == null && root.right.right.val == null){
//            isSymmetric = true;
//            if(root.left.left.val == root.right.right.val){
//                isSymmetric = true;
//            }
//        }
//        if(root.left.right.val == root.right.left.val){
//            isSymmetric = true;
//        }
//        boolean leftResult = isSymmetric(root.left);
//        boolean rightResult = isSymmetric(root.right);
//        if(leftResult == true && rightResult == true){
//            return isSymmetric;
//        }

//        return isSymmetric;
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
    public static TreeNode flatten(TreeNode root) {
        if (root == null) { // if root is null return root
            return null;
        }
        Queue<TreeNode> stack = new LinkedList<>(); // create empty stack / queue
        stack.offer(root); // push root into stack
        TreeNode dummy = new TreeNode(-1);// dummy head
        TreeNode current = dummy;
        while (!stack.isEmpty()){
            TreeNode node = stack.poll(); // remove  1 treenode from queue
            current.right = new TreeNode(node.val);
            current.left = null;// set dummy treenode right node value to queued value
            current = current.right; // set current treenode to right node

            if(node.right != null){ // if right node is not null
                stack.offer(node.right); // add to stack/ queue
            }
            if(node.left != null){
                stack.offer(node.left);
            }
        }

        return dummy.right;

    }
    public static List<Double> averageOfLevels(TreeNode root){
        if(root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Double> result = new ArrayList<>();
        while(!queue.isEmpty()) {
            double sum = 0;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(sum / n);
        }
        return result;

    }
    public static int sum(TreeNode root, int sum){
        if(root == null){
            return 0;
        }
         sum = sum * 10 + root.val; // multiply inital value by 10 then add root value
        if(root.left == null && root.right == null)
            return sum;

        return sum(root.left,sum) + sum(root.right, sum);
    }
    public static boolean hasPathSum(TreeNode root, int targetSum){
        if(root ==  null){
            return false;
        }

//        targetSum = targetSum - root.val;
        if(root.left == null && root.right == null){
            return targetSum == root.val;
        }
        boolean left = hasPathSum(root.left, targetSum - root.val);
        boolean right = hasPathSum(root.right, targetSum - root.val);

// if there is a path for either right or left return true
        return  left || right;
    }

    public static TreeNode buildTree(Integer[] postorder, Integer[] inorder) {
        if (postorder == null || inorder == null) return null;
        int postorderIndex = postorder.length - 1;
        int index = inorder.length - 1;



        TreeNode root = new TreeNode(postorder[postorderIndex--]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);



//        Queue<Integer> queue = new LinkedList<>();
//        for(Integer val : inorder) {
//            queue.add(val);
//        }

//        Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
//        Output: [3,9,20,null,null,15,7]

        while(postorderIndex >= 0 ){
            TreeNode node = new TreeNode(postorder[postorderIndex]);
            TreeNode top = stack.peek();
//            if (stack.peek().val.equals(postorder[postorderIndex])) {
//                postorderIndex--;
//                digit = postorder[postorderIndex];
//            }
            if (!top.val.equals(inorder[index])) {
                top.right = node;
                stack.push(node);
                postorderIndex--;
                }  else {
                    while (!stack.isEmpty() && stack.peek().val.equals(inorder[index])) {
                        top = stack.pop();
                        index--;
                        }
                    top.left = node;
                    stack.push(node);
                    postorderIndex--;
                }
            }
        return root;
        }

//            flatList = flatList.right;
//            root = root.left;
//            if(root.right != null){
//                flatList.right = new TreeNode(root.left.val);
//                flatList = flatList.right;
//                flatList.right = new TreeNode(root.right.val);
//                root = root.left;


        //        TreeNode[] result = new TreeNode[numbers.length];
//        for (int j = 0; j < numbers.length; j++) {
//            result[j] = new TreeNode(numbers[j]);
//        }


//        flatten(root.left);
//        flatten(root.right);
//return flatList;
//    }



  public static void main(String[] args) {
      TreeNode p = makeTreeNode(new Integer[]{3,9,20,15,7});
      TreeNode q = makeTreeNode(new Integer[]{9,3,15,20,7});
      TreeNode root = makeTreeNode(new Integer[]{1,2,3});
      TreeNode root2 = makeTreeNode(new Integer[]{1,2,2,3,4,4,3});
//      System.out.println(averageOfLevels(root));
//      System.out.println(isSameTree(p,q));
//      System.out.println(invertTree(root));
//      System.out.println(maxDepth(p));
//      System.out.println(root);
//      System.out.println(sum(root, 0));
      Integer[] preOrder = new Integer[]{3,9,20,15,7};
      Integer[] inorder = new Integer[]{9,3,15,20,7};
      Integer[] postorder = new Integer[]{9,15,7,20,3};
      System.out.println(sum(root, 22));
      System.out.println(hasPathSum(root, 5));
      System.out.println(buildTree(postorder, inorder));
//      System.out.println(flatten(root));
//      System.out.println(isSymmetric(root2.left, root2.right));
//          TreeNode = new

  }
  //pre order traversal == visit current node, recursively traverse left subtree then right subtree.
    //in order traversal == traverse current node left subtree, visti current node, then traverser node right subtree.
}
