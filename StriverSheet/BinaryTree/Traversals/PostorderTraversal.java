package StriverSheet.BinaryTree.Traversals;

import java.io.*;
import java.util.*;

import StriverSheet.BinaryTree.TreeNode;

/**
 * ============================================================================
 * Problem   : Binary Tree Postorder Traversal (Iterative - 2 Stacks)
 * Sheet     : Striver A2Z > Binary Trees > Theory / Traversals
 * Link      : https://leetcode.com/problems/binary-tree-postorder-traversal/ (LC 145)
 * ============================================================================
 *
 * Statement:
 *   Given the root of a binary tree, return the postorder traversal of its
 *   nodes' values (Left -> Right -> Root).
 *
 * Example 1:
 *   Input : root = [1,null,2,3]
 *   Output: [3,2,1]
 *
 * Example 2:
 *   Input : root = [1,2,3,4,5,null,8,null,null,6,7,9]
 *   Output: [4,6,7,5,2,9,8,3,1]
 *
 * Example 3:
 *   Input : root = []
 *   Output: []
 *
 * Constraints:
 *   - The number of nodes in the tree is in the range [0, 100].
 *   - -100 <= Node.val <= 100
 *
 * Follow-up: Recursive solution is trivial, could you do it iteratively?
 *
 * Approach (Two Stacks):
 *   1. Push root onto stk1.
 *   2. While stk1 is not empty: pop a node, push it onto stk2, then push its
 *      left child and then its right child onto stk1.
 *      -> Nodes enter stk2 in the order Root -> Right -> Left.
 *   3. Popping everything from stk2 reverses that order, giving
 *      Left -> Right -> Root, which is exactly postorder.
 *
 * Complexity:
 *   Time  : O(N)  - each node is pushed/popped once on each stack.
 *   Space : O(N)  - stk2 ends up holding all N nodes.
 *
 * Input format (input.txt): one line, LeetCode level order, e.g. [1,null,2,3]
 * ============================================================================
 */
public class PostorderTraversal {

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> postorder = new ArrayList<Integer>();
        Stack<TreeNode> stk1 = new Stack<TreeNode>();
        Stack<TreeNode> stk2 = new Stack<TreeNode>();
        TreeNode node = root;

        if(node == null) return postorder;

        stk1.push(node);
        while(!stk1.isEmpty()){
            node = stk1.pop();
            stk2.push(node);
            if(node.left != null) stk1.push(node.left);
            if(node.right != null) stk1.push(node.right);
        }

        while(!stk2.isEmpty()){
            postorder.add(stk2.pop().val);
        }

        return postorder;
    }

    public static void main(String[] args) {

        File file = new File("input.txt");

        try (Scanner scanner = new Scanner(new FileReader(file));
                PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);) {

            System.setOut(out);

            TreeNode root = TreeNode.buildTree(scanner.nextLine());

            System.out.println(postorderTraversal(root));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
