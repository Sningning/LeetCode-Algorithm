package knapsack01;

/**
 * 背包问题
 * 动态规划改进: 只使用一维数组，因为某个位置只考虑上面和左边的内容，所以可以从右往左刷新
 *
 * 时间复杂度: O(n * C) 其中n为物品个数; C为背包容积
 * 空间复杂度: O(C), 只使用了C的额外空间
 *
 * @Author: Song Ningning
 * @Date: 2020-05-21 17:26
 */
public class knapsack01_O2 {

    public int knapsack(int[] w, int[] v, int C) {

        if (w == null || v == null || w.length != v.length)
            throw new IllegalArgumentException("Invalid w or v");

        if (C <= 0)
            throw new IllegalArgumentException("C must be greater or equal to zero.");

        int n = w.length;
        if (n == 0 || n > C)
            return 0;

        int[] dp = new int[C + 1];
        // j 表示当前考虑的背包的容量
        for (int j = 0; j <= C; j++) {
            dp[j] = j >= w[0] ? v[0] : 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = C; j >= w[i]; j--) {  // 从后往前看，如果当前背包容量已经小于物品重量，那么前面的都不能放置了
                dp[j] = Math.max(dp[j], v[i] + dp[j - w[i]]);
            }
        }
        return dp[C];
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
