package StriverSheet.BinaryTree.MediumProblems;

import java.io.*;
import java.util.*;

import StriverSheet.BinaryTree.TreeNode;

/**
 * ============================================================================
 * Problem   : Maximum Depth of Binary Tree (Height of a Binary Tree)
 * Sheet     : Striver A2Z > Binary Trees > Medium Problems
 * Link      : https://leetcode.com/problems/maximum-depth-of-binary-tree/ (LC 104)
 * ============================================================================
 *
 * Statement:
 *   Given the root of a binary tree, return its maximum depth.
 *   A binary tree's maximum depth is the number of nodes along the longest
 *   path from the root node down to the farthest leaf node.
 *
 * Example 1:
 *   Input : root = [3,9,20,null,null,15,7]
 *   Output: 3
 *
 * Example 2:
 *   Input : root = [1,null,2]
 *   Output: 2
 *
 * Constraints:
 *   - The number of nodes in the tree is in the range [0, 10^4].
 *   - -100 <= Node.val <= 100
 *
 * Approach (Recursive DFS - postorder):
 *   1. An empty tree has depth 0 (base case).
 *   2. Recursively find the depth of the left subtree (lh) and right
 *      subtree (rh).
 *   3. Depth of the current node = 1 (itself) + max(lh, rh).
 *   This "compute from children, then combine at the root" pattern is the
 *   base for Balanced Tree, Diameter and Max Path Sum.
 *
 * Complexity:
 *   Time  : O(N)  - every node is visited once.
 *   Space : O(H)  - recursion stack (O(N) skewed, O(log N) balanced).
 *
 * Input format (input.txt): one line, LeetCode level order, e.g. [3,9,20,null,null,15,7]
 * ============================================================================
 */
public class MaximumDepth {

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

            System.out.println(maxDepth(root));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
