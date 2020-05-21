package knapsack01;

/**
 * 背包问题
 * 动态规划改进: 滚动数组, 第i行元素只依赖于第i-1行元素。理论上，只需要保持两行元素。
 *
 * 时间复杂度: O(n * C) 其中n为物品个数; C为背包容积
 * 空间复杂度: O(C), 实际使用了 2*C 的额外空间
 *
 * @Author: Song Ningning
 * @Date: 2020-05-21 17:26
 */
public class knapsack01_O1 {

    public int knapsack(int[] w, int[] v, int C) {

        if (w == null || v == null || w.length != v.length)
            throw new IllegalArgumentException("Invalid w or v");

        if (C <= 0)
            throw new IllegalArgumentException("C must be greater or equal to zero.");

        int n = w.length;
        if (n == 0 || n > C)
            return 0;

        int[][] dp = new int[2][C + 1];
        // j 表示当前考虑的背包的容量
        for (int j = 0; j <= C; j++) {
            dp[0][j] = j >= w[0] ? v[0] : 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= C; j++) {
                dp[i ^ 1][j] = dp[(i - 1) ^ 1][j];
                if (j >= w[i]) {
                    dp[i ^ 1][j] = Math.max(dp[i ^ 1][j], v[i] + dp[(i - 1) ^ 1][j - w[i]]);
                }
            }
        }
        return dp[(n - 1) ^ 1][C];
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
