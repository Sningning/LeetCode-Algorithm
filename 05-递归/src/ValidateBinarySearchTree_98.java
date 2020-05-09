/**
 * 98. 验证二叉搜索树【中等】
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 *
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-02 12:46
 */
public class ValidateBinarySearchTree_98 {

    // 配合 1. 中序遍历 / 递归，定义全局变量
    // ArrayList<Integer> list = new ArrayList<>();
    // boolean result = true;

    // 配合 3. 递归
    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {


        // 1. 中序遍历 / 递归
        // 二叉搜索树的中序遍历结果是有序的，可以先中序遍历二叉树，然后比较元素大小

        // if (root == null) return true;
        // inOrder(root);
        // return result;



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


        // 3. 递归 / 中序遍历
        // 注意：仅检查 node.right.val > node.val 和 node.left.val < node.val 是不够的
        // 应该保证 node.right.val > node.val 且 node 的所有右孩子都应该 大于 node.val

        // if (root == null) {
        //     return true;
        // }
        // if (isValidBST(root.left)) {
        //     if (pre < root.val) {
        //         pre = root.val;
        //         return isValidBST(root.right);
        //     }
        // }
        // return false;

        if (root == null)
            return true;
        // 如果 root 的左孩子不是 BST，返回 false
        if (!isValidBST(root.left))
            return false;
        // 如果 root 左孩子是 BST
        // 再看 root.val
        if (root.val <= pre)
            return false;
        // 如果 root 的左孩子是 BST，且 root.val 满足条件
        // 则更新 root，看 root 的右孩子是不是 BST
        pre = root.val;
        return isValidBST(root.right);


    }

    // private void inOrder(TreeNode node) {
    //
    //     if (node == null) return;
    //     if (node.left != null)
    //         inOrder(node.left);
    //     if (list.size() > 0 && list.get(list.size() - 1) >= node.val) {
    //         result = false;
    //         return;
    //     }
    //     list.add(node.val);
    //     if (node.right != null)
    //         inOrder(node.right);
    // }
}
