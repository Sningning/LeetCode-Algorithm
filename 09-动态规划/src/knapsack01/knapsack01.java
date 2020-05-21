package knapsack01;

/**
 * 背包问题
 * 动态规划
 * 时间复杂度: O(n * C) 其中n为物品个数; C为背包容积
 * 空间复杂度: O(n * C)
 *
 * @Author: Song Ningning
 * @Date: 2020-05-21 16:56
 */
public class knapsack01 {

    public int knapsack(int[] w, int[] v, int C) {

        if (w == null || v == null || w.length != v.length)
            throw new IllegalArgumentException("Invalid w or v");

        if (C <= 0)
            throw new IllegalArgumentException("C must be greater or equal to zero.");

        int n = w.length;
        if (n == 0 || n > C)
            return 0;

        int[][] dp = new int[n][C + 1];
        // j 表示当前考虑的背包的容量
        for (int j = 0; j <= C; j++) {
            dp[0][j] = j >= w[0] ? v[0] : 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= w[i]) {
                    dp[i][j] = Math.max(dp[i][j], v[i] + dp[i - 1][j - w[i]]);
                }
            }
        }
        return dp[n - 1][C];
    }

    public static void main(String[] args) {

        int[] w = {1, 2, 3};
        int[] v = {6, 10, 12};
        int C = 5;
        knapsack01 bag = new knapsack01();
        int res = bag.knapsack(w, v, C);  // 预期 22
        System.out.println(res);
    }
}
