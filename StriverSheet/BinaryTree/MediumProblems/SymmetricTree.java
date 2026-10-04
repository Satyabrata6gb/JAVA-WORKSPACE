package StriverSheet.BinaryTree.MediumProblems;

import java.io.*;
import java.util.*;

import StriverSheet.BinaryTree.TreeNode;

/**
 * ============================================================================
 * Problem   : Symmetric Tree
 * Sheet     : Striver A2Z > Binary Trees > Medium Problems
 * Link      : https://leetcode.com/problems/symmetric-tree/ (LC 101)
 * ============================================================================
 *
 * Statement:
 *   Given the root of a binary tree, check whether it is a mirror of itself
 *   (i.e., symmetric around its center).
 *
 * Example 1:
 *   Input : root = [1,2,2,3,4,4,3]
 *   Output: true
 *
 * Example 2:
 *   Input : root = [1,2,2,null,3,null,3]
 *   Output: false
 *
 * Constraints:
 *   - The number of nodes in the tree is in the range [1, 1000].
 *   - -100 <= Node.val <= 100
 *
 * Follow-up: Could you solve it both recursively and iteratively?
 *
 * Approach (Same Tree check, but with children crossed):
 *   1. The tree is symmetric iff the left subtree is a mirror image of the
 *      right subtree.
 *   2. Two subtrees p and q are mirrors iff
 *        - both are null, or
 *        - p.val == q.val
 *          AND p.left mirrors q.right (outer pair)
 *          AND p.right mirrors q.left (inner pair).
 *   3. This is exactly isSameTree with the second argument's children
 *      swapped.
 *
 * Complexity:
 *   Time  : O(N)  - every node is compared once.
 *   Space : O(H)  - recursion stack.
 *
 * Helper renamed: isSameTree -> isMirror (it compares p.left with q.right,
 *   so it checks for mirror images, not identical trees).
 *
 * Input format (input.txt): one line, LeetCode level order, e.g. [1,2,2,3,4,4,3]
 * ============================================================================
 */
public class SymmetricTree {

    public static boolean isSymmetric(TreeNode root) {
        if(root == null) return true;

        return isMirror(root.left, root.right);
    }

    public static boolean isMirror(TreeNode p, TreeNode q) {
        if(p == null || q == null){
            return (p == q);
        }

        return (p.val == q.val) && isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }

    public static void main(String[] args) {

        File file = new File("input.txt");

        try (Scanner scanner = new Scanner(new FileReader(file));
                PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);) {

            System.setOut(out);

            TreeNode root = TreeNode.buildTree(scanner.nextLine());

            System.out.println(isSymmetric(root));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
