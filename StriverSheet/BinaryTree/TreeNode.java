package StriverSheet.BinaryTree;

import java.util.*;

/**
 * Shared binary tree node (same shape as LeetCode's TreeNode) used by all
 * problems under StriverSheet.BinaryTree.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) { this.val = val; }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * Builds a tree from LeetCode's level-order format.
     * Accepts "[1,null,2,3]", "1,null,2,3" or "1 null 2 3". "[]" gives null.
     */
    public static TreeNode buildTree(String line) {
        String[] tokens = line.replace("[", "").replace("]", "").trim().split("[,\\s]+");
        if (tokens.length == 0 || tokens[0].isEmpty() || tokens[0].equals("null")) return null;

        TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (!queue.isEmpty() && i < tokens.length) {
            TreeNode node = queue.poll();

            if (i < tokens.length && !tokens[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(tokens[i]));
                queue.offer(node.left);
            }
            i++;

            if (i < tokens.length && !tokens[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(tokens[i]));
                queue.offer(node.right);
            }
            i++;
        }

        return root;
    }
}
