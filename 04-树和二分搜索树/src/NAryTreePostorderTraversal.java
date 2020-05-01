import java.util.ArrayList;
import java.util.List;

/**
 * 590. N叉树的后序遍历
 * 给定一个 N 叉树，返回其节点值的后序遍历。
 *
 * https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-01 14:41
 */
public class NAryTreePostorderTraversal {

    public List<Integer> postorder(Node root) {

        // 1. 递归

        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;


        // 2. 迭代


    }

    private void postOrder(Node node, List<Integer> list) {
        if (node == null) return;
        for (Node child : node.children) {
            postOrder(child, list);
        }
        list.add(node.val);
    }
}
