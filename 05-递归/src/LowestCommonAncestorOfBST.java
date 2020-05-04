/**
 * 235. 二叉搜索树的最近公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 *
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-04 10:53
 */
public class LowestCommonAncestorOfBST {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root.val > p.val && root.val > q.val)
            // p 和 q 都在当前节点的左侧
            return lowestCommonAncestor(root.left, p, q);
        else if (root.val < p.val && root.val < q.val)
            // p 和 q 都在当前节点的左侧
            return lowestCommonAncestor(root.right, p, q);
        else
            // 包含了 root == null、root == p、root == q 这三种递归终止情况
            // 以及 p 和 q 分别在左右子树的情况
            return root;
    }
}
