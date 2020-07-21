import java.util.LinkedList;
import java.util.List;

/**
 * 95. 不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 *
 * 示例：
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 *
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 提示：
 * 0 <= n <= 8
 *
 * https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
 *
 * @author: Song Ningning
 * @date: 2020-07-21 14:34
 */
public class UniqueBinarySearchTreesII_95 {

    /**
     * 递归
     *
     * 每个数 i 都可以构成根节点，因此遍历所有的数，构建根节点；
     * 左侧 [1...i-1] 构建左子树，右侧 [i+1...n] 构建右子树。
     * 当某一个根节点固定为 i 时，其实左子树又有很多种情况，右子树也有很多种情况，将左右子树每种情况的根节点存入 list 中，
     * 这样，根节点为 i 时，就可以得到左右子树的所有情况，放在了两个 list 中：left 和 right，接下来就让左右子树进行组合，
     * 总的个数就是 left.size() * right.size()。
     */
    static class Solution1 {

        public List<TreeNode> generateTrees(int n) {
            if (n < 1) {
                return new LinkedList<>();
            }
            return dfs(1, n);
        }

        private List<TreeNode> dfs(int start, int end) {
            List<TreeNode> list = new LinkedList<>();
            if (start > end) {
                // 如果当前子树为空，不加 null 行吗？
                list.add(null);
                return list;
            }

            // 选择所有可能的根结点
            for (int i = start; i <= end; i++) {
                // 想想为什么构建根节点不能放在这里，而放在下面？
                // TreeNode root = new TreeNode(i);
                List<TreeNode> left = dfs(start, i - 1);
                List<TreeNode> right = dfs(i + 1, end);

                // 固定左孩子，遍历右孩子
                for (TreeNode l : left) {
                    for (TreeNode r : right) {
                        TreeNode root = new TreeNode(i);
                        root.left = l;
                        root.right = r;
                        list.add(root);
                    }
                }
            }
            return list;
        }

        /**
         * 在下面两层 for 循环遍历时，每种情况应该对应不同的根节点（val是一样的，但是内存地址不一样），
         * 如果构建根节点放在上面的话，下面每种情况最终都是使用的一个根节点（内存地址一样）。
         *
         * 如果当前子树为空，不加入 list 中的话，list 就是空的，两层 for 循环遍历左右孩子时直接无法进行。
         */
    }
}
