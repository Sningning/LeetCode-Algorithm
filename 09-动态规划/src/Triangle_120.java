import java.util.List;

/**
 * 120. 三角形最小路径和
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。
 *
 * 例如，给定三角形：
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * 自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。
 *
 * 说明：
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 * https://leetcode-cn.com/problems/triangle/
 *
 * @author: Song Ningning
 * @date: 2020-07-14 12:06
 */
public class Triangle_120 {


    /**
     * 动态规划
     * 状态：dp[i][j] 表示从点 (i, j) 到底边的最小路径和。
     * 自底向上计算
     * dp[i][j] = min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle(i)(j);
     */
    static class Solution3 {
        public int minimumTotal(List<List<Integer>> triangle) {
            int n = triangle.size();
            int[][] dp = new int[n][n];
            // 先把最后一行填写
            for (int j = 0; j < n; j++) {
                dp[n - 1][j] = triangle.get(n - 1).get(j);
            }
            for (int i = n - 2; i >= 0; i--) {
                for (int j = 0; j < triangle.get(i).size(); j++) {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
                }
            }
            return dp[0][0];
        }
    }


    /**
     * 由于 dp[i][j] 只依赖于 dp[i + 1][j], dp[i + 1][j + 1]，所以可以进行状态压缩
     */
    static class Solution4 {
        public int minimumTotal(List<List<Integer>> triangle) {
            int n = triangle.size();
            int[] dp = new int[n];
            // 先把最后一行填写
            for (int j = 0; j < n; j++) {
                dp[j] = triangle.get(n - 1).get(j);
            }
            for (int i = n - 2; i >= 0; i--) {
                for (int j = 0; j < triangle.get(i).size(); j++) {
                    dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
                }
            }
            return dp[0];
        }
    }
}
