/**
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 示例:
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * https://leetcode-cn.com/problems/minimum-path-sum/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-26 12:24
 */
public class MinimumPathSum_64 {

    // 暴力递归
    // 大数组超时
    static class Solution1 {
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0)
                return 0;
            return walk(grid, 0, 0);
        }

        private int walk(int[][] grid, int i, int j) {
            // base case : 从右下角走到右下角
            if (i == grid.length - 1 && j == grid[0].length - 1) {
                return grid[i][j];
            }
            // 在最后一行
            if (i == grid.length - 1) {
               return grid[i][j] + walk(grid, i, j + 1);
            }
            // 在最后一列
            if (j == grid[0].length - 1) {
                return grid[i][j] + walk(grid, i + 1, j);
            }
            // 往下走
            int down = walk(grid, i + 1, j);
            // 往右走
            int right = walk(grid, i, j + 1);
            return grid[i][j] + Math.min(down, right);
        }

        public static void main(String[] args) {
            Solution1 s = new Solution1();
            int[][] grid = {
                    {1, 3, 1},
                    {1, 5, 1},
                    {4, 2, 1}
            };
            System.out.println(s.minPathSum(grid));
        }
    }


    // 记忆化搜索
    // 用备忘录记录每次计算的路径和，每次尝试时先从备忘录中查找是否已经计算过
    // 没有计算过才进行计算
    static class Solution2 {
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0)
                return 0;
            int[][] memo = new int[grid.length][grid[0].length];
            return walk(grid, 0, 0, memo);
        }

        private int walk(int[][] grid, int i, int j, int[][] memo) {
            // base case : 从右下角走到右下角
            if (i == grid.length - 1 && j == grid[0].length - 1) {
                // memo[i][j] = grid[i][j];
                return grid[i][j];
            }
            // 先从备忘录里寻找
            if (memo[i][j] != 0) {
                return memo[i][j];
            }

            // 在最后一行
            if (i == grid.length - 1) {
                memo[i][j] = grid[i][j] + walk(grid, i, j + 1, memo);
                return memo[i][j];
            }
            // 在最后一列
            if (j == grid[0].length - 1) {
                memo[i][j] = grid[i][j] + walk(grid, i + 1, j, memo);
                return memo[i][j];
            }
            // 往下走
            int down = walk(grid, i + 1, j, memo);
            // 往右走
            int right = walk(grid, i, j + 1, memo);
            memo[i][j] = grid[i][j] + Math.min(down, right);
            return memo[i][j];
        }

        public static void main(String[] args) {
            Solution2 s = new Solution2();
            int[][] grid = {
                    {1, 3, 1},
                    {1, 5, 1},
                    {4, 2, 1}
            };
            System.out.println(s.minPathSum(grid));
        }
    }

    /*
    * 动态规划
    * 状态：dp[i][j] 表示从 (i,j) 到达右下角的最短路径和
    * 初始化：dp[M-1][N-1] == grid[M-1][N-1]，该状态不依赖于其他状态
    *     最后一行只能往右走，dp[M-1][j] = grid[M-1][j] + dp[M-1][j+1]
    *     最后一列只能往下走，dp[i][N-1] = grid[i][N-1] + dp[i+1][N-1]
    * 状态转移：dp[i][j] = grid[i][j] + Math.min(dp[i+1][j], dp[i][j+1])
    *        由于 dp[i][j] 参考其右边和下边的路径和，因此填表顺序:
    *           从倒数第二列开始，从下往上，依次填写每一列 或者
    *           从倒数第二行开始，从右往左，依次填写每一行
    * 输出：dp[0][0]
    */

    static class Solution3 {
        public int minPathSum(int[][] grid) {
            if (grid == null || grid.length == 0)
                return 0;
            int M = grid.length;
            int N = grid[0].length;

            int[][] dp = new int[M][N];

            // 初始化

            dp[M-1][N-1] = grid[M-1][N-1];
            // 填最后一行
            for (int j = N - 2; j >= 0; j--) {
                dp[M - 1][j] = grid[M - 1][j] + dp[M - 1][j + 1];
            }
            // 填最后一列
            for (int i = M - 2; i >= 0; i--) {
                dp[i][N - 1] = grid[i][N - 1] + dp[i + 1][N - 1];
            }

            // 状态转移
            for (int j = N - 2; j >= 0 ; j--) {
                for (int i = M - 2; i >= 0; i--) {
                    dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
                }
            }
            return dp[0][0];
        }

        public static void main(String[] args) {
            Solution3 s = new Solution3();
            int[][] grid = {
                    {1, 3, 1},
                    {1, 5, 1},
                    {4, 2, 1}
            };
            System.out.println(s.minPathSum(grid));
        }
    }
}
