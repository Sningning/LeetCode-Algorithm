import java.util.ArrayList;

/**
 * 98. 验证二叉搜索树
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-02 12:46
 */
public class ValidateBinarySearchTree {

    // 配合 1. 中序遍历 / 递归，定义全局变量
    ArrayList<Integer> list = new ArrayList<>();
    boolean result = true;

    // 配合 3. 递归
    // long last = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {


        // 1. 中序遍历 / 递归
        // 二叉搜索树的中序遍历结果是有序的，可以先中序遍历二叉树，然后比较元素大小

        if (root == null) return true;
        inOrder(root);
        return result;



        // 2. 中序遍历 / 迭代

        // Deque<TreeNode> stack = new ArrayDeque<>();
        // TreeNode node = root;
        // long value = Long.MIN_VALUE;
        // while (node != null || !stack.isEmpty()) {
        //     while (node != null) {
        //         stack.push(node);
        //         node = node.left;
        //     }
        //     node = stack.pop();
        //     if (node.val <= value) return false;
        //     value = node.val;
        //     node = node.right;
        // }
        // return true;


        // 3. 递归
        // 注意：仅检查 node.right.val > node.val 和 node.left.val < node.val 是不够的
        // 应该保证 node.right.val > node.val 且 node 的所有右孩子都应该 大于 node.val

        // if (root == null) {
        //     return true;
        // }
        // if (isValidBST(root.left)) {
        //     if (last < root.val) {
        //         last = root.val;
        //         return isValidBST(root.right);
        //     }
        // }
        // return false;



    }

    private void inOrder(TreeNode node) {

        if (node == null) return;
        if (node.left != null)
            inOrder(node.left);
        if (list.size() > 0) {
            if (list.get(list.size() - 1) >= node.val)
                result = false;
        }
        list.add(node.val);
        if (node.right != null)
            inOrder(node.right);
    }
}
