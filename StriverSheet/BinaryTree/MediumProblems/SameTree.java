package StriverSheet.BinaryTree.MediumProblems;

import java.io.*;
import java.util.*;

import StriverSheet.BinaryTree.TreeNode;

/**
 * ============================================================================
 * Problem   : Same Tree (Check if two trees are identical)
 * Sheet     : Striver A2Z > Binary Trees > Medium Problems
 * Link      : https://leetcode.com/problems/same-tree/ (LC 100)
 * ============================================================================
 *
 * Statement:
 *   Given the roots of two binary trees p and q, check if they are the same.
 *   Two binary trees are the same if they are structurally identical and the
 *   nodes have the same value.
 *
 * Example 1:
 *   Input : p = [1,2,3], q = [1,2,3]
 *   Output: true
 *
 * Example 2:
 *   Input : p = [1,2], q = [1,null,2]
 *   Output: false
 *
 * Example 3:
 *   Input : p = [1,2,1], q = [1,1,2]
 *   Output: false
 *
 * Constraints:
 *   - The number of nodes in both trees is in the range [0, 100].
 *   - -10^4 <= Node.val <= 10^4
 *
 * Approach (Simultaneous recursive traversal):
 *   1. If either node is null, the trees match here only if BOTH are null
 *      -> return (p == q).
 *   2. Otherwise the trees are the same iff
 *        p.val == q.val
 *        AND left subtrees are the same
 *        AND right subtrees are the same.
 *   3. && short-circuits, so the recursion stops at the first mismatch.
 *
 * Complexity:
 *   Time  : O(min(N, M)) - stops at the first mismatch; O(N) when identical.
 *   Space : O(H)         - recursion stack.
 *
 * Input format (input.txt): two lines, one tree per line in LeetCode level order
 *   [1,2,3]
 *   [1,2,3]
 * ============================================================================
 */
public class SameTree {

    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null || q == null){
            return (p == q);
        }

        return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {

        File file = new File("input.txt");

        try (Scanner scanner = new Scanner(new FileReader(file));
                PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);) {

            System.setOut(out);

            TreeNode p = TreeNode.buildTree(scanner.nextLine());
            TreeNode q = TreeNode.buildTree(scanner.nextLine());

            System.out.println(isSameTree(p, q));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
