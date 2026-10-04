package StriverSheet.BinaryTree.MediumProblems;

import java.io.*;
import java.util.*;

import StriverSheet.BinaryTree.TreeNode;

/**
 * ============================================================================
 * Problem   : Balanced Binary Tree
 * Sheet     : Striver A2Z > Binary Trees > Medium Problems
 * Link      : https://leetcode.com/problems/balanced-binary-tree/ (LC 110)
 * ============================================================================
 *
 * Statement:
 *   Given a binary tree, determine if it is height-balanced.
 *   A height-balanced binary tree is one in which the depth of the two
 *   subtrees of EVERY node never differs by more than one.
 *
 * Example 1:
 *   Input : root = [3,9,20,null,null,15,7]
 *   Output: true
 *
 * Example 2:
 *   Input : root = [1,2,2,3,3,null,null,4,4]
 *   Output: false
 *
 * Example 3:
 *   Input : root = []
 *   Output: true
 *
 * Constraints:
 *   - The number of nodes in the tree is in the range [0, 5000].
 *   - -10^4 <= Node.val <= 10^4
 *
 * Approach (Top-down: check height difference at every node):
 *   1. An empty tree is balanced.
 *   2. Compute the heights of the left (lh) and right (rh) subtrees using
 *      maxDepth(). If |lh - rh| > 1, the current node is unbalanced.
 *   3. Otherwise the tree is balanced only if both subtrees are themselves
 *      balanced -> recurse on left and right.
 *
 * Complexity:
 *   Time  : O(N^2) worst case - maxDepth() is O(subtree size) and is called
 *           again at every node (O(N log N) for an already balanced tree).
 *   Space : O(H)   - recursion stack.
 *
 * Optimization (O(N)): fold the check into the height function itself -
 *   return -1 from height() as soon as any subtree is unbalanced, otherwise
 *   return 1 + max(lh, rh). Then isBalanced = (height(root) != -1).
 *
 * Input format (input.txt): one line, LeetCode level order, e.g. [3,9,20,null,null,15,7]
 * ============================================================================
 */
public class BalancedBinaryTree {

    public static boolean isBalanced(TreeNode root) {
        if(root == null) return true;

        int lh = maxDepth(root.left);
        int rh = maxDepth(root.right);

        if(Math.abs(lh - rh) > 1) return false;

        Boolean left = isBalanced(root.left);
        Boolean right = isBalanced(root.right);

        return left && right;
    }

    public static int maxDepth(TreeNode root) {
        if(root == null) return 0;

        int lh = maxDepth(root.left);
        int rh = maxDepth(root.right);

        return 1 + Math.max(lh , rh);
    }

    public static void main(String[] args) {

        File file = new File("input.txt");

        try (Scanner scanner = new Scanner(new FileReader(file));
                PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);) {

            System.setOut(out);

            TreeNode root = TreeNode.buildTree(scanner.nextLine());

            System.out.println(isBalanced(root));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
