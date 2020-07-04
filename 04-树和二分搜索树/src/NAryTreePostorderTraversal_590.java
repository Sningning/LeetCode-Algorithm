import java.util.ArrayList;
import java.util.List;

/**
 * 590. N 叉树的后序遍历【简单】
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 *
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-01 14:41
 */
public class NAryTreePostorderTraversal_590 {

    public List<Integer> postorder(Node root) {

        // 1. 递归

        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;


        // 2. 迭代
        // 先序遍历的变形，先序遍历是“根->左->右”，后序遍历是“左->右->根”，
        // 那么把先序遍历改成“根->右->左”，再逆序一下就是后序遍历。

        // LinkedList<Integer> res = new LinkedList<>();
        // if (root == null) return res;
        // Deque<Node> stack = new ArrayDeque<>();
        // stack.push(root);
        // while (!stack.isEmpty()) {
        //     Node node = stack.pop();
        //     res.addFirst(node.val);
        //     for (int i = 0; i < node.children.size(); i++) {
        //         if (node.children.get(i) != null)
        //             stack.push(node.children.get(i));
        //     }
        // }
        // return res;


    }

    private void postOrder(Node node, List<Integer> list) {
        if (node == null) return;
        for (Node child : node.children) {
            postOrder(child, list);
        }
        list.add(node.val);
    }
}
