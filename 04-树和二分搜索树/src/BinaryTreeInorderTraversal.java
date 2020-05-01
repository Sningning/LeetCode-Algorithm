import java.util.ArrayList;
import java.util.List;

/**
 * 94. 二叉树的中序遍历
 * 给定一个二叉树，返回它的中序 遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-01 10:44
 */
public class BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {

        // 1. 递归

        // List<Integer> res = new ArrayList<>();
        // inOrder(root, res);
        // return res;


        // 2. 迭代



    }

    // private void inOrder(TreeNode node, List<Integer> list) {
    //     if (node == null) return;
    //     if (node.left != null)
    //         inOrder(node.left, list);
    //     list.add(node.val);
    //     if (node.right != null)
    //         inOrder(node.right, list);
    // }

}
