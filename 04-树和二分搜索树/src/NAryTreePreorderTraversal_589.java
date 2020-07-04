import java.util.ArrayList;
import java.util.List;

/**
 * 589. N 叉树的前序遍历【简单】
 * 给定一个 N 叉树，返回其节点值的前序遍历。
 *
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/description/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-01 14:23
 */
public class NAryTreePreorderTraversal_589 {

    public List<Integer> preorder(Node root) {

        // 1. 递归

        List<Integer> res = new ArrayList<>();
        preOrder(root, res);
        return res;


        // 2. 迭代

        // LinkedList<Integer> res = new LinkedList<>();
        // if (root == null) return res;
        // Deque<Node> stack = new ArrayDeque<>();
        // stack.push(root);
        // while (!stack.isEmpty()) {
        //     Node node = stack.pop();
        //     res.addLast(node.val);
        //     for (int i = node.children.size() - 1; i >= 0; i--)
        //         stack.push(node.children.get(i));
        // }
        // return res;

    }

    private void preOrder(Node node, List<Integer> list) {
        if (node == null) return;
        list.add(node.val);
        for (Node child : node.children)
            preOrder(child, list);
    }


}
