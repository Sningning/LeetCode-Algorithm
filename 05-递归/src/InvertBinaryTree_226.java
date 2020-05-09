/**
 * 226. 翻转二叉树【简单】
 * 翻转一棵二叉树。
 *
 * https://leetcode-cn.com/problems/invert-binary-tree/description/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-02 12:10
 */
public class InvertBinaryTree_226 {

    public TreeNode invertTree(TreeNode root) {

        // 1. 递归
        return invert(root);


        // 2. 迭代
        // 采用层序遍历的方法，每次调换当前结点的左右孩子

        // if (root == null) return null;
        // Queue<TreeNode> queue = new LinkedList<>();
        // queue.add(root);
        // while (!queue.isEmpty()) {
        //     TreeNode cur = queue.poll();
        //     TreeNode temp = cur.left;
        //     cur.left = cur.right;
        //     cur.right = temp;
        //     if (cur.left != null) queue.add(cur.left);
        //     if (cur.right != null) queue.add(cur.right);
        // }
        // return root;

    }

    private TreeNode invert(TreeNode node) {

        // 终止条件
        if (node == null) return null;

        // 递归调用翻转左右结点
        TreeNode temp = invert(node.left);
        node.left = invert(node.right);
        node.right = temp;
        return node;
    }
}
