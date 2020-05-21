/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为 至少 两个正整数的和，并使这些整数的乘积最大化。
 * 返回你可以获得的最大乘积。
 *
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 * @Author: Song Ningning
 * @Date: 2020-05-21 8:44
 */
public class IntegerBreak_343 {

    /**
     * 1. 暴力递归(超时)
     */
    static class Solution1 {

        public int integerBreak(int n) {
            return breadInteger(n);
        }

        // 将 n 进行分割（至少两部分），可以获得的最大乘积
        private int breadInteger(int n) {
            if (n == 1)
                return 1;
            int res = -1;
            for (int i = 1; i <= n - 1; i++)
                res = Math.max(res, Math.max(i * (n - i), i * breadInteger(n - i)));
            return res;
        }
    }


    /**
     * 2. 记忆化搜索---自顶向下
     */
    static class Solution2 {

        public int integerBreak(int n) {
            int[] memo = new int[n + 1];
            return breakInteger(n, memo);
        }

        private int breakInteger(int n, int[] memo) {
            if (n == 1)
                return 1;
            if (memo[n] != 0)
                return memo[n];
            int res = -1;
            for (int i = 1; i <= n - 1; i++) {
                res = Math.max(res, Math.max(i * (n - i), i * breakInteger(n - i, memo)));
                memo[n] = res;
            }
            return res;
        }
    }


    /**
     * 3. 动态规划---自底向上
     */
    static class Solution3 {

        public int integerBreak(int n) {
            // dp[i] 表示将 i 进行分割（至少两部分），可以获得的最大乘积
            int[] dp = new int[n + 1];
            dp[1] = 1;
            // 要计算 dp[n], 就要计算 dp[1],dp[2]...
            for (int i = 2; i <= n; i++) {
                // 对于每一个 i，还要分解为 j + (i - j)，计算出 dp[i]
                for (int j = 1; j <= i - 1; j++) {
                    // 获得最大乘积的一种方式 j * (i - j)
                    // 另一种方式 (i - j) 分割后的最大乘积 * j，而 (i - j) 分割后的最大乘积就是 dp[i - j]
                    // 因为 (i - j) < i，因此之前已经求出了
                    dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
                }
            }
            return dp[n];
        }
    }
}