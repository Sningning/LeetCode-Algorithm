import java.util.ArrayList;
import java.util.List;

/**
 * 145. 二叉树的后序遍历【困难】
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-01 11:56
 */
public class BinaryTreePostorderTraversal_145 {

    public List<Integer> postorderTraversal(TreeNode root) {

        // 1.递归

        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;


        // 2. 迭代

        // 前序遍历顺序为：根 -> 左 -> 右
        // 后序遍历顺序为：左 -> 右 -> 根
        // 如果1： 我们将前序遍历中结点插入结果链表尾部的逻辑，修改为将结点插入结果链表的头部
        //         那么结果链表就变为了：右 -> 左 -> 根
        // 如果2： 我们将遍历的顺序由从左到右修改为从右到左，配合如果1
        //         那么结果链表就变为了：左 -> 右 -> 根
        // 这刚好是后序遍历的顺序，即，把前序遍历改成“根->右->左”，再逆序一下就是后序遍历。
        // 如何处理：
        // 修改前序遍历代码中，每次先查看左结点再查看右结点的逻辑，变为先查看右结点再查看左结点
        // 修改前序遍历代码中，结点写入结果链表的代码，将插入队尾修改为插入队首

        // LinkedList<Integer> res = new LinkedList<>();
        // if (root == null) return res;
        // Deque<TreeNode> stack = new ArrayDeque<>();
        // stack.push(root);
        // while (!stack.isEmpty()) {
        //     TreeNode node = stack.pop();
        //     // 头插
        //     res.addFirst(node.val);
        //     if (node.left != null)
        //         stack.push(node.left);
        //     if (node.right != null)
        //         stack.push(node.right);
        // }
        // return res;


        // LinkedList<Integer> res = new LinkedList<>();
        // Deque<TreeNode> stack = new ArrayDeque<>();
        // TreeNode node = root;
        // while (node != null || !stack.isEmpty()) {
        //     while (node != null) {
        //         res.addFirst(node.val);
        //         stack.push(node);
        //         node = node.right;
        //     }
        //     node = stack.pop().left;
        // }
        // return res;



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
