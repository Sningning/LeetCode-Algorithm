import java.util.Arrays;

/**
 * 62. 不同路径
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 问总共有多少条不同的路径？
 *
 * 示例 1:
 * 输入: m = 3, n = 2
 * 输出: 3
 * 解释:
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 *
 * 示例 2:
 * 输入: m = 7, n = 3
 * 输出: 28
 *  
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10 ^ 9
 *
 * 链接：https://leetcode-cn.com/problems/unique-paths
 *
 * @Author: Song Ningning
 * @Date: 2020-07-06 9:43
 */
public class UniquePaths_62 {

    /**
     * 递归
     * 超时
     */
    static class Solution1 {
        public int uniquePaths(int m, int n) {
            return dfs(0, 0, m, n);
        }

        private int dfs(int x, int y, int m, int n) {
            if (x == m - 1 && y == n - 1) {
                return 1;
            }
            int down = 0;
            int right = 0;
            // 向下探索
            if (x + 1 < m) {
                down = dfs(x + 1, y, m, n);
            }
            // 向右探索
            if (y + 1 < n) {
                right = dfs(x, y + 1, m, n);
            }
            return down + right;
        }
    }


    /**
     * 递归 + 备忘录
     */
    static class Solution2 {
        public int uniquePaths(int m, int n) {
            int[][] memo = new int[m][n];
            return dfs(0, 0, m, n, memo);
        }

        private int dfs(int x, int y, int m, int n, int[][] memo) {
            if (x == m - 1 && y == n - 1) {
                return 1;
            }
            int down = 0;
            int right = 0;
            // 先检查备忘录
            if (memo[x][y] != 0) {
                return memo[x][y];
            }
            // 向下探索
            if (x + 1 < m) {
                down = dfs(x + 1, y, m, n, memo);
            }
            // 向右探索
            if (y + 1 < n) {
                right = dfs(x, y + 1, m, n, memo);
            }
            // 先存起来，再返回
            memo[x][y] = down + right;
            return memo[x][y];
        }
    }


    /**
     * 动态规划
     *
     * dp[i][j] 表示从起始点走到 (i,j) 的路径数；
     * 由于只能向右和向下移动，因此到达 (i,j) 只可能从 (i-1,j) 和 (i,j-1),
     * 因此 dp[i][j] = dp[i-1][j] + dp[i][j-1];
     * 第一行的位置 (0,j) 只能是从左侧到达，同理，第一列的位置 (i,0) 只能从上面到达，
     * 因此 dp 数组第一行和第一列是可以直接填好的，中间位置可以从上面到达或从左边到达。
     * 最后返回 dp[m-1][m-1] 即可。
     */
    static class Solution3 {
        public int uniquePaths(int m, int n) {
            int[][] dp = new int[m][n];
            // 填第一行
            for (int j = 0; j < n; j++) {
                dp[0][j] = 1;
            }
            // 填第一列
            for (int i = 0; i < m; i++) {
                dp[i][0] = 1;
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
            return dp[m - 1][n - 1];
        }
    }


    /**
     * 动态规划，状态压缩
     *
     * 由于 (i,j) 取决于 (i-1, j) 和 (i,j-1);
     * 如果一行一行的求解的话，(i-1,j) 相当于上一步求出的结果，而 (i, j-1) 是上一轮求出的结果
     * 可以使用一个一维数组，比如一行一行求解，一维 dp 数组大小为 n；
     * 首先每个位置填 1，如同二维数组中第一行，完成第一行的求解；
     * 第二行求解，利用数组已有信息，求 dp[i]，此时 dp[i] 中是有数据的，结果就是二维数组中 (i-1,j) 的值，
     * 而之前二维数组中 (i-1, j) 的值就是 i 左侧的值
     * 因此，dp[i] = dp[i] + dp[i-1]，相当于二维数组中 dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
     */
    static class Solution4 {
        public int uniquePaths(int m, int n) {
            int[] dp = new int[n];
            Arrays.fill(dp, 1);
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
            return dp[n - 1];
        }
    }
}
