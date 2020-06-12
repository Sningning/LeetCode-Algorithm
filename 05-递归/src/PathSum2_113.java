import java.util.LinkedList;
import java.util.List;

/**
 * 113. 路径总和 II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * https://leetcode-cn.com/problems/path-sum-ii/
 *
 * @Author: Song Ningning
 * @Date: 2020-06-12 15:08
 */

public class PathSum2_113 {

    /**
     * 回溯
     */
    static class Solution1 {

        List<List<Integer>> res = new LinkedList<>();

        public List<List<Integer>> pathSum(TreeNode root, int sum) {
    
            if (root == null) {
                return res;
            }
            List<Integer> path = new LinkedList<>();
            backtrack(root, sum, path);
            return res;
        }
    
        private void backtrack(TreeNode node, int sum, List<Integer> path) {
            // 递归终止条件：到达了根结点且满足条件
            if (node.left == null && node.right == null && node.val == sum) {
                path.add(node.val);
                res.add(new LinkedList<>(path));
                path.remove(path.size() - 1);
                return;
            }
            // 递归终止条件：到达了根结点但不满足条件
            if (node.left == null && node.right == null) {
                return;
            }
    
            // 能运行到这里，说明 node 不是根结点，将当前结点的值加入 path
            path.add(node.val);
    
            // 左孩子不为空就继续走
            if (node.left != null) {
                backtrack(node.left, sum - node.val, path);
            }
            // 右孩子不为空就继续走
            if (node.right != null) {
                backtrack(node.right, sum - node.val, path);
            }
            path.remove(path.size() - 1);
        }
    }


    /**
     * 上面的解法，在 backtrack 函数中，递归终止的条件比较繁琐，因为是只递归到叶子结点，
     * 其实完全可以递归到空
     */
    static class Solution2 {

        List<List<Integer>> res = new LinkedList<>();

        public List<List<Integer>> pathSum(TreeNode root, int sum) {

            List<Integer> path = new LinkedList<>();
            backtrack(root, sum, path);
            return res;
        }

        private void backtrack(TreeNode node, int sum, List<Integer> path) {
            if (node == null) {
                return;
            }
            path.add(node.val);
            if (node.left == null && node.right == null && node.val == sum) {
                res.add(new LinkedList<>(path));
                path.remove(path.size() - 1);
                return;
            }
            // 到了此处，node 可能是不满足条件的叶子结点，那么进入下面两个递归都是直接 return 了；
            // 也可能是非叶子结点，继续完成递归。
            backtrack(node.left, sum - node.val, path);
            backtrack(node.right, sum - node.val, path);
            path.remove(path.size() - 1);
        }


        /**
         * 也可以删掉 path.remove(path.size() - 1); return;
         * 因为是叶子结点了，再递归进去的话，其实也是直接 return，然后最后再执行一次 path.remove(path.size() - 1);
         * 精简两行以后相当于多递归了一层
         */
        private void backtrack1(TreeNode node, int sum, List<Integer> path) {
            if (node == null) {
                return;
            }
            path.add(node.val);
            if (node.left == null && node.right == null && node.val == sum) {
                res.add(new LinkedList<>(path));
            }
            // 到了此处，node 可能是不满足条件的叶子结点，那么进入下面两个递归都是直接 return 了；
            // 也可能是非叶子结点，继续完成递归。
            backtrack(node.left, sum - node.val, path);
            backtrack(node.right, sum - node.val, path);
            path.remove(path.size() - 1);
        }
    }
}