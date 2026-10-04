package StriverSheet.BinaryTree.Traversals;

import java.io.*;
import java.util.*;

import StriverSheet.BinaryTree.TreeNode;

/**
 * ============================================================================
 * Problem   : Binary Tree Inorder Traversal (Iterative)
 * Sheet     : Striver A2Z > Binary Trees > Theory / Traversals
 * Link      : https://leetcode.com/problems/binary-tree-inorder-traversal/ (LC 94)
 * ============================================================================
 *
 * Statement:
 *   Given the root of a binary tree, return the inorder traversal of its
 *   nodes' values (Left -> Root -> Right).
 *
 * Example 1:
 *   Input : root = [1,null,2,3]
 *   Output: [1,3,2]
 *
 * Example 2:
 *   Input : root = [1,2,3,4,5,null,8,null,null,6,7,9]
 *   Output: [4,2,6,5,7,1,3,9,8]
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
 * Approach (Iterative using one Stack):
 *   1. Keep a pointer `node` starting at root.
 *   2. While `node` is not null, push it and go left (dive to the leftmost).
 *   3. When `node` becomes null, the stack top is the next node in inorder:
 *        - if the stack is empty, traversal is complete -> break.
 *        - else pop it, record its value, and move to its right subtree.
 *   4. Repeat. The stack simulates the recursion call stack.
 *
 * Complexity:
 *   Time  : O(N)  - every node is pushed and popped exactly once.
 *   Space : O(H)  - stack holds at most one root-to-leaf path
 *                   (O(N) for a skewed tree, O(log N) for a balanced one).
 *
 * Input format (input.txt): one line, LeetCode level order, e.g. [1,null,2,3]
 * ============================================================================
 */
public class InorderTraversal {

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorder = new ArrayList<Integer>();
        Stack<TreeNode> stk = new Stack<TreeNode>();
        TreeNode node = root;

        while(true){
            if(node != null){
                stk.push(node);
                node = node.left;
            }
            else{
                if(stk.isEmpty()){
                    break;
                }

                node = stk.pop();
                inorder.add(node.val);
                node = node.right;
            }
        }

        return inorder;
    }

    public static void main(String[] args) {

        File file = new File("input.txt");

        try (Scanner scanner = new Scanner(new FileReader(file));
                PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);) {

            System.setOut(out);

            TreeNode root = TreeNode.buildTree(scanner.nextLine());

            System.out.println(inorderTraversal(root));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
