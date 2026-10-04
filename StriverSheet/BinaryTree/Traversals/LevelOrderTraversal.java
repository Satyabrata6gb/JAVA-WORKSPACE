package StriverSheet.BinaryTree.Traversals;

import java.io.*;
import java.util.*;

import StriverSheet.BinaryTree.TreeNode;

/**
 * ============================================================================
 * Problem   : Binary Tree Level Order Traversal (BFS)
 * Sheet     : Striver A2Z > Binary Trees > Theory / Traversals
 * Link      : https://leetcode.com/problems/binary-tree-level-order-traversal/ (LC 102)
 * ============================================================================
 *
 * Statement:
 *   Given the root of a binary tree, return the level order traversal of its
 *   nodes' values (i.e., from left to right, level by level).
 *
 * Example 1:
 *   Input : root = [3,9,20,null,null,15,7]
 *   Output: [[3],[9,20],[15,7]]
 *
 * Example 2:
 *   Input : root = [1]
 *   Output: [[1]]
 *
 * Example 3:
 *   Input : root = []
 *   Output: []
 *
 * Constraints:
 *   - The number of nodes in the tree is in the range [0, 2000].
 *   - -1000 <= Node.val <= 1000
 *
 * Approach (BFS with a Queue, processing one level at a time):
 *   1. If root is null return an empty list; otherwise enqueue root.
 *   2. While the queue is not empty:
 *        - nodesInLevel = queue.size() -> exactly the nodes of the current level.
 *        - Repeat nodesInLevel times: enqueue the front node's left and right
 *          children (if present), then poll it and add its value to `level`.
 *        - Append `level` to the answer.
 *   3. Children added during a level sit behind the current level's nodes,
 *      so each iteration of the outer loop handles exactly one level.
 *
 * Complexity:
 *   Time  : O(N)  - each node is enqueued and dequeued once.
 *   Space : O(N)  - the queue can hold up to ~N/2 nodes (the widest level).
 *
 * Input format (input.txt): one line, LeetCode level order, e.g. [3,9,20,null,null,15,7]
 * ============================================================================
 */
public class LevelOrderTraversal {

    public static List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> levelOrder = new LinkedList<>();
        TreeNode node = root;

        if(node == null) return levelOrder;

        queue.offer(root);
        while(!queue.isEmpty()){
            List<Integer> level = new LinkedList<>();
            int nodesInLevel = queue.size();
            for(int i = 0 ; i < nodesInLevel; i++){
                if(queue.peek().left != null) queue.offer(queue.peek().left);
                if(queue.peek().right != null) queue.offer(queue.peek().right);
                level.add(queue.poll().val);
            }

            levelOrder.add(level);
        }

        return levelOrder;
    }

    public static void main(String[] args) {

        File file = new File("input.txt");

        try (Scanner scanner = new Scanner(new FileReader(file));
                PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);) {

            System.setOut(out);

            TreeNode root = TreeNode.buildTree(scanner.nextLine());

            System.out.println(levelOrder(root));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
