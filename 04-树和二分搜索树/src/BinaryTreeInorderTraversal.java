import java.util.ArrayList;
import java.util.List;

/**
 * 94. 二叉树的中序遍历【中等】
 * 给定一个二叉树，返回它的中序 遍历
 *
 * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-01 10:44
 */
public class BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {

        // 1. 递归。
        // 时间复杂度：O(N)，递归函数 T(N) = 2·T(N/2) + O(1)；空间复杂度：最坏O(N)，平均O(logN)。

        List<Integer> res = new ArrayList<>();
        inOrder(root, res);
        return res;


        // 2. 迭代
        // 根入栈 -> 左孩子入栈 -> 左孩子出栈 -> 根出栈 -> 右孩子入栈 -> 右孩子出栈

        // List<Integer> res = new ArrayList<>();
        // Deque<TreeNode> stack = new ArrayDeque<>();
        // TreeNode node = root;
        // while (node != null || !stack.isEmpty()) {
        //     // 不断往左子树方向走，每走一次就将当前结点保存到栈中
        //     // 这是模拟递归的调用
        //     while (node != null) {
        //         stack.push(node);
        //         node = node.left;
        //     }
        //     // 当前结点为空，说明左边走到头了，从栈中弹出结点并保存
        //     // 然后转向右边结点，继续上面整个过程
        //     node = stack.pop();
        //     res.add(node.val);
        //     node = node.right;
        // }
        // return res;



        // 另一种迭代

        // 使用颜色标记结点的状态，新结点为白色，已访问的结点为灰色。
        // 如果遇到的结点为白色，则将其标记为灰色，然后将其右子结点、自身、左子结点依次入栈。
        // 如果遇到的结点为灰色，则将结点的值输出。

        // class ColorNode {
        //     TreeNode node;
        //     int color;  // 0 为白色，代表新结点；1 为灰色，代表已访问
        //
        //     public ColorNode(TreeNode node, int color) {
        //         this.node = node;
        //         this.color =  color;
        //     }
        // }
        //
        // List<Integer> res = new ArrayList<>();
        // if (root == null) return res;
        // Deque<ColorNode> stack = new ArrayDeque<>();
        // stack.push(new ColorNode(root, 0));
        // while (!stack.isEmpty()) {
        //     ColorNode cn = stack.pop();
        //     if (cn.color == 0) {
        //         if (cn.node.right != null)
        //             stack.push(new ColorNode(cn.node.right, 0));
        //         stack.push(new ColorNode(cn.node, 1));
        //         if (cn.node.left != null)
        //             stack.push(new ColorNode(cn.node.left, 0));
        //     } else {
        //         res.add(cn.node.val);
        //     }
        // }
        // return res;



    }

    private void inOrder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        if (node.left != null)
            inOrder(node.left, list);
        list.add(node.val);
        if (node.right != null)
            inOrder(node.right, list);
    }

}
