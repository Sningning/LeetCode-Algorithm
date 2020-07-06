/**
 * 63. 不同路径 II
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 说明：m 和 n 的值均不超过 100。
 *
 * 示例 1:
 * 输入:
 * [
 *   [0,0,0],
 *   [0,1,0],
 *   [0,0,0]
 * ]
 * 输出: 2
 * 解释:
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 *
 * @Author: Song Ningning
 * @Date: 2020-07-06 21:51
 */
public class UniquePaths2_63 {

    /**
     * 单纯递归会超时，加上备忘录即可
     */
    static class Solution1 {
        int m;
        int n;
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if (obstacleGrid == null || obstacleGrid.length == 0) {
                return 0;
            }
            m = obstacleGrid.length;
            n = obstacleGrid[0].length;
            int[][] memo = new int[m][n];
            return dfs(obstacleGrid, 0, 0, memo);
        }

        private int dfs(int[][] grid, int x, int y, int[][] memo) {
            if (x == m - 1 && y == n - 1) {
                return grid[x][y] == 0 ? 1 : 0;
            }
            if (grid[x][y] == 0) {
                int down = 0;
                int right = 0;
                // 先检查备忘录
                if (memo[x][y] != 0) {
                    return memo[x][y];
                }
                // 向下探索
                if (x + 1 < m) {
                    down = dfs(grid, x + 1, y, memo);
                }
                // 向右探索
                if (y + 1 < n) {
                    right = dfs(grid, x, y + 1, memo);
                }
                // 先存起来，再返回
                memo[x][y] = down + right;
                return memo[x][y];
            }
            return 0;
        }
    }

    /**
     * 动态规划
     * 与 62 题基本一样，加了个障碍条件
     *
     * dp[i][j] 表示从起始点走到 (i,j) 的路径数；
     * 由于只能向右和向下移动，因此到达 (i,j) 只可能从 (i-1,j) 和 (i,j-1),
     * 因此 如果 (i, j) 位置没有障碍物，则 dp[i][j] = dp[i-1][j] + dp[i][j-1];
     * 第一行的位置 (0,j) 只能是从左侧到达，同理，第一列的位置 (i,0) 只能从上面到达，
     * 因此 dp 数组第一行和第一列是可以直接填好的，中间位置可以从上面到达或从左边到达。
     * 最后返回 dp[m-1][m-1] 即可。
     */
    static class Solution2 {
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0][0] == 1) {
                return 0;
            }
            int m = obstacleGrid.length;
            int n = obstacleGrid[0].length;
            int[][] dp = new int[m][n];
            // 填第一行
            for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
                dp[0][j] = 1;
            }
            // 填第一列
            for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
                dp[i][0] = 1;
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (obstacleGrid[i][j] == 0) {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
            return dp[m - 1][n - 1];
        }
    }
}
