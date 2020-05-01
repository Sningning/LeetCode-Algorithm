import java.util.*;

/**
 * 429. N叉树的层序遍历
 * 给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
 *
 * https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-01 13:59
 */
public class NAryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(Node root) {


        // 1. 队列

        if (root == null) return new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 保存每一层的列表，并且在根节点为空时正常工作
            List<Integer> level = new ArrayList<>();
            // size 记录队列的当前大小, 用另一个循环来处理 size 数量的节点
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.val);
                // 用 addAll 将孩子结点一次性全部加入队列
                queue.addAll(node.children);
            }
            res.add(level);
        }
        return res;



    }
}
