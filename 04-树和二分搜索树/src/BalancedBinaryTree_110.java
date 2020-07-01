/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 *
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 *
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 *
 * https://leetcode-cn.com/problems/balanced-binary-tree/
 */
public class BalancedBinaryTree_110 {

    /**
     * 自顶向下计算
     *
     * 从根结点出发，对于每个结点，计算其左右深度，然后判断。
     */
    static class Solution1 {
        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }
            int leftDepth = getTreeDepth(root.left);
            int rightDepth = getTreeDepth(root.right);
            if (Math.abs(leftDepth - rightDepth) > 1) {
                return false;
            }
            return isBalanced(root.left) && isBalanced(root.right);
        }

        /**
         * 求以 root 为根的二叉树的最大深度
         * @param root 根结点
         * @return 二叉树的最大深度
         */
        private int getTreeDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = getTreeDepth(root.left);
            int right = getTreeDepth(root.right);
            return Math.max(left, right) + 1;
        }
    }


    /**
     * 自底向上
     *
     * 方法一中明显有重复计算
     * 当执行完 getTreeDepth(root.left) 后，我们已经求出了左子树的深度，
     * 但是后面执行 isBalanced(root.left) 又会计算一遍；
     * 而且执行 getTreeDepth(root.left) 时，getTreeDepth 方法也会计算出 root.left 的左子树和右子树的深度，
     * 此时 root.left 是不是平衡二叉树其实已经可以知道了，因此在 getTreeDepth 方法中就可以返回一些信息。
     * 即 一边求解结点深度，一边判断结点是否平衡。
     *
     * isBalanced 中只需求一个深度即可。
     *
     * 此方法其实是相当于后续遍历，后序遍历先考虑左右子树，最后考虑根结点，在考虑左右子树时就可以判定该子树是否是平衡二叉树。
     *
     * 时间复杂度：O(N)，最坏情况下遍历所有结点。
     * 空间复杂度：O(N)，最差情况下（树退化为链表时），系统递归需要使用 O(N) 的栈空间。
     *
     * 一个比较好的题解：
     * https://leetcode-cn.com/problems/balanced-binary-tree/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-25/
     *
     * 官方题解动画演示：
     * https://leetcode-cn.com/problems/balanced-binary-tree/solution/ping-heng-er-cha-shu-by-leetcode/
     */
    static class Solution2 {
        public boolean isBalanced(TreeNode root) {
            // if (getTreeDepth(root) == -1) {
            //     return false;
            // }
            // return true;

            // 简写为下面一行代码
            return getTreeDepth(root) != -1;
        }

        /**
         * 求以 root 为根的二叉树的最大深度
         * @param root 根结点
         * @return 以 root 为根的二叉树的最大深度，如果左右子树深度相差超过 1，返回 -1
         */
        private int getTreeDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int left = getTreeDepth(root.left);
            int right = getTreeDepth(root.right);
            // 如果左子树不是平衡二叉树，直接返回
            if (left == -1) {
                return -1;
            }
            // 如果右子树不是平衡二叉树，直接返回
            if (right == -1) {
                return -1;
            }
            // 上面两个判断不能省略
            // 当 left = -1，right = -1 时，abs(left - right) = 0
            if (Math.abs(left - right) > 1) {
                return -1;
            }
            return Math.max(left, right) + 1;
        }
    }
}