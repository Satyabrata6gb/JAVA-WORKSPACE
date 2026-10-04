package StriverSheet.BinaryTree.MediumProblems;

import java.io.*;
import java.util.*;

import StriverSheet.BinaryTree.TreeNode;

/**
 * ============================================================================
 * Problem   : Diameter of Binary Tree
 * Sheet     : Striver A2Z > Binary Trees > Medium Problems
 * Link      : https://leetcode.com/problems/diameter-of-binary-tree/ (LC 543)
 * ============================================================================
 *
 * Statement:
 *   Given the root of a binary tree, return the length of the diameter of
 *   the tree. The diameter is the length of the longest path between any two
 *   nodes in the tree. This path may or may not pass through the root.
 *   The length of a path is the number of EDGES between the nodes.
 *
 * Example 1:
 *   Input : root = [1,2,3,4,5]
 *   Output: 3
 *   Explanation: 3 is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Example 2:
 *   Input : root = [1,2]
 *   Output: 1
 *
 * Constraints:
 *   - The number of nodes in the tree is in the range [1, 10^4].
 *   - -100 <= Node.val <= 100
 *
 * Approach (Height DFS that also tracks the best diameter):
 *   1. The longest path that "bends" at a node goes down its left subtree
 *      and down its right subtree, so its length in edges = lh + rh,
 *      where lh / rh are the heights of the left / right subtrees.
 *   2. Run the normal height recursion; at every node update
 *      max[0] = max(max[0], lh + rh).
 *   3. Return 1 + max(lh, rh) as the height to the parent.
 *   4. max is an int[1] so updates made deep in the recursion are visible
 *      to the caller (Java passes primitives by value).
 *
 * Complexity:
 *   Time  : O(N)  - single pass, each node visited once.
 *   Space : O(H)  - recursion stack.
 *
 * Helper renamed: findMax -> heightAndUpdateDiameter (it returns the height
 *   and records the diameter as a side effect).
 *
 * Input format (input.txt): one line, LeetCode level order, e.g. [1,2,3,4,5]
 * ============================================================================
 */
public class DiameterOfBinaryTree {

    public static int diameterOfBinaryTree(TreeNode root) {
        if(root == null) return 0;

        int[] max = new int[1];

        heightAndUpdateDiameter(root, max);

        return max[0];
    }

    public static int heightAndUpdateDiameter(TreeNode root, int[] max) {
        if(root == null) return 0;

        int lh = heightAndUpdateDiameter(root.left, max);
        int rh = heightAndUpdateDiameter(root.right, max);

        max[0] = Math.max(lh+rh, max[0]);

        return 1 + Math.max(lh , rh);
    }

    public static void main(String[] args) {

        File file = new File("input.txt");

        try (Scanner scanner = new Scanner(new FileReader(file));
                PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);) {

            System.setOut(out);

            TreeNode root = TreeNode.buildTree(scanner.nextLine());

            System.out.println(diameterOfBinaryTree(root));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
