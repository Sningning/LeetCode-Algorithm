import java.util.Deque;
import java.util.LinkedList;

/**
 * 112. 路径总和 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * 
 * 说明: 叶子节点是指没有子节点的节点。
 * 
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * 
 * https://leetcode-cn.com/problems/path-sum/
 */

public class PathSum_112 {


    /**
     * 递归 
     * 
     * 这道题有个坑，题目中说是否存在根结点到叶子结点的路径，也就是说一定要找到叶子结点才算完。 
     * 比如上面的例子，如果给定的 sum = 20，有一条路径 5-4-11，相加是 20，但是 11 不是叶子结点，所以这条路径也是 false。
     * 知道这个以后，就明白每次都需要走到叶子结点。
     *  
     * 递归终止条件：走到了根结点，可以进行判断了。
     */
    static class Solution1 {
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) {
                return false;
            }
            return hasPathSumHelper(root, sum);
        }

        private boolean hasPathSumHelper(TreeNode root, int sum) {
            // 递归终止条件：到达叶子节点
            if (root.left == null && root.right == null) {
                return root.val == sum;
            }
            // 左孩子为 null
            if (root.left == null) {
                return hasPathSumHelper(root.right, sum - root.val);
            }
            // 右孩子为 null
            if (root.right == null) {
                return hasPathSumHelper(root.left, sum - root.val);
            }
            return hasPathSumHelper(root.left, sum - root.val) || hasPathSumHelper(root.right, sum - root.val);
        }

    }


    /**
     * 可以把上面 hasPathSumHelper 的情况合并下，这样递归终止的条件可以设置为 2 个：
     *   1. 到达了叶子结点；
     *   2. 当前结点为空，返回 false
     * 对于当前考虑的结点为空的话有 2 种情况：
     *   1. 根结点，直接返回 false；
     *   2. 一个结点传入的空的左结点或右结点，对应于下面这种情况：
     *        1 
     *       /
     *      2 
     *    1 结点将其右孩子传入递归函数，由于 1 不是子结点，因此右侧的判断也会终止。
     */
    static class Solution2 {
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) {
                return false;
            }
            // 已经到达了叶子结点
            if (root.left == null && root.right == null) {
                return root.val == sum;
            }
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }
    }

}