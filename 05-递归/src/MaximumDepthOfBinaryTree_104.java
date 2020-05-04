/**
 * 104. 二叉树的最大深度【简单】
 * 给定一个二叉树，找出其最大深度。
 *
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-02 15:45
 */
public class MaximumDepthOfBinaryTree_104 {

    public int maxDepth(TreeNode root) {

        // 递归终止条件：叶子节点，深度为 0
        if (root == null) return 0;

        // 递归求解左右孩子的最大深度
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        // 当前结点深度为左右孩子中较大深度 + 1
        return Math.max(left, right) + 1;

    }
}
