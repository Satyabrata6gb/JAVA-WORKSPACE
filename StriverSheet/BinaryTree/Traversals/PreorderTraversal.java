package StriverSheet.BinaryTree.Traversals;

import java.io.*;
import java.util.*;

import StriverSheet.BinaryTree.TreeNode;

/**
 * ============================================================================
 * Problem   : Binary Tree Preorder Traversal (Iterative)
 * Sheet     : Striver A2Z > Binary Trees > Theory / Traversals
 * Link      : https://leetcode.com/problems/binary-tree-preorder-traversal/ (LC 144)
 * ============================================================================
 *
 * Statement:
 *   Given the root of a binary tree, return the preorder traversal of its
 *   nodes' values (Root -> Left -> Right).
 *
 * Example 1:
 *   Input : root = [1,null,2,3]
 *   Output: [1,2,3]
 *
 * Example 2:
 *   Input : root = [1,2,3,4,5,null,8,null,null,6,7,9]
 *   Output: [1,2,4,5,6,7,3,8,9]
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
 * Approach (Iterative using one Stack - same skeleton as inorder):
 *   1. Keep a pointer `node` starting at root.
 *   2. While `node` is not null: record its value FIRST (root is visited
 *      before its children), push it, then go left.
 *   3. When `node` becomes null:
 *        - if the stack is empty, traversal is complete -> break.
 *        - else pop the last node and move to its right subtree.
 *   4. Only difference from inorder: the value is added at push time
 *      instead of pop time.
 *
 * Complexity:
 *   Time  : O(N)  - every node is pushed and popped exactly once.
 *   Space : O(H)  - stack holds at most one root-to-leaf path.
 *
 * Input format (input.txt): one line, LeetCode level order, e.g. [1,null,2,3]
 * ============================================================================
 */
public class PreorderTraversal {

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> inorder = new ArrayList<Integer>();
        Stack<TreeNode> stk = new Stack<TreeNode>();
        TreeNode node = root;

        while(true){
            if(node != null){
                stk.push(node);
                inorder.add(node.val);
                node = node.left;
            }
            else{
                if(stk.isEmpty()){
                    break;
                }

                node = stk.pop();
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

            System.out.println(preorderTraversal(root));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
