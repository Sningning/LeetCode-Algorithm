import java.util.LinkedList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 * https://leetcode-cn.com/problems/binary-tree-paths/
 *
 * @Author: Song Ningning
 * @Date: 2020-06-12 17:03
 */
public class BinaryTreePaths_257 {

    /**
     * 递归回溯
     */
    List<String> res = new LinkedList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        List<Integer> path = new LinkedList<>();
        backtrack(root, path);
        return res;
    }

    private void backtrack(TreeNode node, List<Integer> path) {
        if (node == null) {
            return;
        }
        path.add(node.val);
        // 到达了叶子结点
        if (node.left == null && node.right == null) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                builder.append(path.get(i));
                builder.append("->");
            }
            // 下面两行是删掉最后一个箭头
            builder.deleteCharAt(builder.length() - 1);
            builder.deleteCharAt(builder.length() - 1);
            res.add(builder.toString());
        }
        backtrack(node.left, path);
        backtrack(node.right, path);
        path.remove(path.size() - 1);
    }
    
}