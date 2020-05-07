import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Song Ningning
 * @Date: 2020-05-07 11:59
 */
public class FindLargestValueInEachTreeRow_515 {

    public List<Integer> largestValues(TreeNode root) {

        // 1. BFS / 队列

        // List<Integer> res = new LinkedList<>();
        // if (root == null)
        //     return res;
        // Queue<TreeNode> queue = new ArrayDeque<>();
        // queue.add(root);
        // while (!queue.isEmpty()) {
        //     int max = Integer.MIN_VALUE;
        //     int size = queue.size();
        //     for (int i = 0; i < size; i++) {
        //         TreeNode node = queue.poll();
        //         max = Math.max(max, node.val);
        //         if (node.left != null)
        //             queue.add(node.left);
        //         if (node.right != null)
        //             queue.add(node.right);
        //     }
        //     res.add(max);
        // }
        // return res;


        // 2. DFS

        List<Integer> res = new LinkedList<>();
        if (root == null)
            return res;
        dfs(root, 0, res);
        return res;

    }

    private void dfs(TreeNode node, int level, List<Integer> res) {

    }
}
