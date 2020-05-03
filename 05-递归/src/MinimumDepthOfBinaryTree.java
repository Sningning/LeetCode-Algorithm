/**
 * 111. 二叉树的最小深度【简单】
 * 给定一个二叉树，找出其最小深度。
 *
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-02 16:05
 */
public class MinimumDepthOfBinaryTree {

    public int minDepth(TreeNode root) {

        // 最大深度能够保证最后一个节点绝对是叶子节点，而最小深度不行。
        // 注意题目给出的最短路径定义：是根节点到 叶子节点 的最短路径，比如：
        //   1
        //    \
        //     2
        // 1 不是叶子节点，2 是叶子节点，所以最小深度是 1 到 2 的深度，为 2，而不是 1

        // 递归
        if (root == null) return 0;

        int left = minDepth(root.left);
        int right = minDepth(root.right);

        // 判断当前节点是不是叶子节点

        // 如果左孩子和右孩子其中一个为空，那么需要返回较大的那个孩子的深度 + 1
        // 如果都为空，那么直接返回 1
        // 如果其中一个节点为空，说明 left 和 right 有一个必然为 0，所以可以返回 left + right + 1
        // 如果都为空，left 和 right 都为 0，还是可以返回 left + right + 1
        if (root.left == null || root.right == null)
            return left + right + 1;
        // 左右孩子都不为空，返回最小深度 +1
        return Math.min(left, right) + 1;
    }
}
