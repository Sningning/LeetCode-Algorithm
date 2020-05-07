import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 515. 在每个树行中找最大值
 * 您需要在二叉树的每一行中找到最大的值。
 *
 * https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-07 11:59
 */
public class FindLargestValueInEachTreeRow_515 {

    public List<Integer> largestValues(TreeNode root) {

        // 1. BFS / 队列

        List<Integer> res = new LinkedList<>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int max = Integer.MIN_VALUE;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            res.add(max);
        }
        return res;


        // 2. DFS

        // List<Integer> res = new LinkedList<>();
        // if (root == null)
        //     return res;
        // dfs(root, 1, res);
        // return res;

    }

    // private void dfs(TreeNode node, int depth, List<Integer> res) {
    //
    //     if (node == null) {
    //         return;
    //     }
    //     // 判断是否是当前层，如果 depth > res.size()
    //     // 说明进入了新的一层
    //     if (res.size() < depth) {
    //         res.add(Integer.MIN_VALUE);
    //     }
    //     // res 中当前层存储的最大值和当前结点的值比较，取大者
    //     int max = Math.max(res.get(depth - 1), node.val);
    //     // 更新当前层的最大值
    //     res.set(depth - 1, max);
    //     dfs(node.left, depth + 1, res);
    //     dfs(node.right, depth + 1, res);
    // }
}
