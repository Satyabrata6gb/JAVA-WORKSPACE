package StriverSheet.BinaryTree.MediumProblems;

import java.io.*;
import java.util.*;

import StriverSheet.BinaryTree.TreeNode;

/**
 * ============================================================================
 * Problem   : Binary Tree Maximum Path Sum
 * Sheet     : Striver A2Z > Binary Trees > Medium Problems
 * Link      : https://leetcode.com/problems/binary-tree-maximum-path-sum/ (LC 124, Hard)
 * ============================================================================
 *
 * Statement:
 *   A path in a binary tree is a sequence of nodes where each pair of
 *   adjacent nodes has an edge connecting them. A node can appear in the
 *   sequence at most once. The path does not need to pass through the root.
 *   The path sum is the sum of the node values in the path.
 *   Given the root of a binary tree, return the maximum path sum of any
 *   non-empty path.
 *
 * Example 1:
 *   Input : root = [1,2,3]
 *   Output: 6
 *   Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 6.
 *
 * Example 2:
 *   Input : root = [-10,9,20,null,null,15,7]
 *   Output: 42
 *   Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 42.
 *
 * Constraints:
 *   - The number of nodes in the tree is in the range [1, 3 * 10^4].
 *   - -1000 <= Node.val <= 1000
 *
 * Approach (Same pattern as Diameter, with sums instead of heights):
 *   1. For each node, let lh / rh = best sum of a path going DOWNWARD from
 *      the left / right child (never negative - see step 3).
 *   2. The best path that bends at this node = root.val + lh + rh.
 *      Update the global answer max[0] with it.
 *   3. Return to the parent the best downward path through this node:
 *      root.val + max(lh, rh). If that is negative, return 0 instead -
 *      the parent is better off not extending into this subtree at all.
 *   4. max[0] starts at -1001 (below the smallest possible node value), so a
 *      tree with all-negative values still returns its largest single node.
 *
 * Complexity:
 *   Time  : O(N)  - single pass, each node visited once.
 *   Space : O(H)  - recursion stack.
 *
 * Helper renamed: findMax -> maxDownwardPathSum (it returns the best
 *   downward path sum starting at this node, and records the best
 *   overall path as a side effect).
 *
 * Input format (input.txt): one line, LeetCode level order, e.g. [-10,9,20,null,null,15,7]
 * ============================================================================
 */
public class MaxPathSum {

    public static int maxPathSum(TreeNode root) {
        if(root == null) return 0;

        int[] max = new int[1];

        max[0] = -1001;

        maxDownwardPathSum(root, max);

        return max[0];
    }

    public static int maxDownwardPathSum(TreeNode root, int[] max) {
        if(root == null) return 0;

        int lh = maxDownwardPathSum(root.left, max);
        int rh = maxDownwardPathSum(root.right, max);

        max[0] = Math.max((root.val + lh + rh), max[0]);

        return (root.val + Math.max(lh , rh)) < 0 ? 0 : root.val + Math.max(lh , rh);
    }

    public static void main(String[] args) {

        File file = new File("input.txt");

        try (Scanner scanner = new Scanner(new FileReader(file));
                PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);) {

            System.setOut(out);

            TreeNode root = TreeNode.buildTree(scanner.nextLine());

            System.out.println(maxPathSum(root));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
