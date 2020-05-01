import java.util.ArrayList;
import java.util.List;

/**
 * 145. 二叉树的后序遍历
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-01 11:56
 */
public class BinaryTreePostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {

        // 1.递归

        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;


        // 2. 迭代



        // 3. 另一种迭代

        // class ColorNode {
        //     TreeNode node;
        //     int color;  // 0 为白色，代表新结点；1 为灰色，代表已访问
        //
        //     public ColorNode(TreeNode node, int color) {
        //         this.node = node;
        //         this.color =  color;
        //     }
        // }
        // List<Integer> res = new ArrayList<>();
        // if (root == null) return res;
        // Deque<ColorNode> stack = new ArrayDeque<>();
        // stack.push(new ColorNode(root, 0));
        // while (!stack.isEmpty()) {
        //     ColorNode cn = stack.pop();
        //     if (cn.color == 0) {
        //         stack.push(new ColorNode(cn.node, 1));
        //         if (cn.node.right != null)
        //             stack.push(new ColorNode(cn.node.right, 0));
        //         if (cn.node.left != null)
        //             stack.push(new ColorNode(cn.node.left, 0));
        //     } else {
        //         res.add(cn.node.val);
        //     }
        // }
        // return res;


    }

    private void postOrder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        postOrder(node.left, list);
        postOrder(node.right, list);
        list.add(node.val);
    }


}
