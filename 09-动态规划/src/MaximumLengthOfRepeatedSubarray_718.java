/**
 * 718. 最长重复子数组
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 *
 * 示例 1:
 *
 * 输入:
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出: 3
 * 解释:
 * 长度最长的公共子数组是 [3, 2, 1]。
 *
 * 说明:
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 *
 * https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
 *
 * @Author: Song Ningning
 * @Date: 2020-07-01 09:24
 */
public class MaximumLengthOfRepeatedSubarray_718 {

    /**
     * 动态规划（填表）
     * 和最长公共子串问题一样
     * dp[i][j] 代表 表示 A[0...i] 和 B[0...j] 的最长公共前缀。
     * 首先要判断 A[i] 是否等于B[j]：
     *     如果不相等，说明当前数字构成公共数组，所以 dp[i][j] = 0；
     *     如果相等，说明可以构成公共数组，还要加上他们前一个数字构成的最长公共数组长度，也就是 dp[i-1][j-1]。
     *     递推公式：如果 A[i] = B[j]，则 dp[i][j] = dp[i-1][j-1] + 1，否则 dp[i][j] == 0。
     * 返回 dp 数组中最大值。
     */
    static class Solution1 {
        public int findLength(int[] A, int[] B) {
            int m = A.length, n = B.length;
            int[][] dp = new int[m][n];
            int res = 0;
            // 填写第一行
            for (int j = 0; j < n; j++) {
                if (A[0] == B[j]) {
                    dp[0][j] = 1;
                    res = Math.max(res, dp[0][j]);
                }
            }
            // 填写第一列
            for (int i = 0; i < m; i++) {
                if (B[0] == A[i]) {
                    dp[i][0] = 1;
                    res = Math.max(res, dp[i][0]);
                }
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (A[i] == B[j]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        res = Math.max(res, dp[i][j]);
                    }
                }
            }
            return res;
        }
    }

    /**
     * dp 数组行列多增加一个元素，就不需要单独填写第一行和第一列
     */
    static class Solution2 {
        public int findLength(int[] A, int[] B) {
            int m = A.length, n = B.length;
            // 多使用一个空间，不用单独去填写每一行每一列
            int[][] dp = new int[m + 1][n + 1];
            int res = 0;
            for (int i = 1; i <= m; i++) { // 注意取等号
                for (int j = 1; j <= n; j++) { // 注意取等号
                    if (A[i - 1] == B[j - 1]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    res = Math.max(res, dp[i][j]);
                }
            }
            return res;
        }
    }


    /**
     * TODO 滚动数组，压缩空间
     */
    static class Solution3 {

    }

}