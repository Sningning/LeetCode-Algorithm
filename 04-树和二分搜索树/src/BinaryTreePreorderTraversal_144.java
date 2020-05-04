import java.util.*;

/**
 * 144. 二叉树的前序遍历【简单】
 * 给定一个二叉树，返回它的 前序 遍历。
 *
 * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-01 10:03
 */
public class BinaryTreePreorderTraversal_144 {

    public List<Integer> preorderTraversal(TreeNode root) {

        // 1. 递归

        List<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;


        // 2. 迭代。时间复杂度：O(N)；空间复杂度:O(N)

        // LinkedList<Integer> res = new LinkedList<>();
        // if (root == null) return res;
        // Deque<TreeNode> stack = new ArrayDeque<>();
        // stack.push(root);
        // while (!stack.isEmpty()) {
        //     TreeNode node = stack.pop();
        //     res.addLast(node.val);
        //     if (node.right != null)
        //         stack.push(node.right);
        //     if (node.left != null)
        //         stack.push(node.left);
        // }
        // return res;

        // LinkedList<Integer> res = new LinkedList<>();
        // Deque<TreeNode> stack = new ArrayDeque<>();
        // TreeNode node = root;
        // while (node != null || !stack.isEmpty()) {
        //     while (node != null) {
        //         res.addLast(node.val);
        //         stack.push(node);
        //         node = node.left;
        //     }
        //     node = stack.pop().right;
        // }
        // return res;


        // 3. 另一种迭代

        // class ColorNode {
        //     TreeNode node;
        //     int color;
        //
        //     public ColorNode(TreeNode node, int color) {
        //         this.node = node;
        //         this.color = color;  // 0 为白色，代表新结点；1 为灰色，代表已访问
        //     }
        // }
        // List<Integer> res = new ArrayList<>();
        // if (root == null) return res;
        // Deque<ColorNode> stack = new ArrayDeque<>();
        // stack.push(new ColorNode(root, 0));
        // while (!stack.isEmpty()) {
        //     ColorNode cn = stack.pop();
        //     if (cn.color == 0) {
        //         if (cn.node.right != null)
        //             stack.push(new ColorNode(cn.node.right, 0));
        //         if (cn.node.left != null)
        //             stack.push(new ColorNode(cn.node.left, 0));
        //         stack.push(new ColorNode(cn.node, 1));
        //     } else {
        //         res.add(cn.node.val);
        //     }
        // }
        // return res;

    }

    private void preOrder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        list.add(node.val);
        preOrder(node.left, list);
        preOrder(node.right, list);
    }

}
