import java.util.*;

/**
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-06 16:23
 */
public class BinaryTreeLevelOrderTraversal_102 {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> levelOrder(TreeNode root) {

        // 1. BFS

        if (root == null) return res;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {

            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            res.add(level);
        }
        return res;


        // 2. DFS

        // dfs(root, 1);
        // return res;
    }

    // private void dfs(TreeNode node, int depth) {
    //
    //     if (node == null) {
    //         return;
    //     }
    //
    //     // 判断是否是当前层，如果 depth > res.size()
    //     // 说明进入了新的一层
    //     if (res.size() < depth) {
    //         res.add(new LinkedList<>());
    //     }
    //     // 在当前层加入当前结点值，层数从 1 开始，对应数组的 index == 0 位置，所以减一
    //     res.get(depth - 1).add(node.val);
    //     dfs(node.left, depth + 1);
    //     dfs(node.right, depth + 1);
    // }
}
