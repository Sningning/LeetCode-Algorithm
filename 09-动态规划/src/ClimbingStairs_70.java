/**
 * 70. 爬楼梯【简单】
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * https://leetcode-cn.com/problems/climbing-stairs/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-25 22:50
 */
public class ClimbingStairs_70 {

    public int climbStairs(int n) {

        // 方法一：斐波那契数。（见递归部分）

        // 方法二：记忆化递归
        // 把每一步的结果存储在 memo 数组之中，
        // 每当函数再次被调用，如果已经存在于 memo 中，就直接从 memo 数组返回结果
        // 否则的话就计算完后，存入 memo 中再返回

        // int[] memo = new int[n + 1];
        // return calcWays(n, memo);


        // 方法三：动态规划
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // 记忆化递归
    // private int calcWays(int n, int[] memo) {
    //     if (n == 0 || n == 1)
    //         return 1;
    //     if (memo[n] == 0)
    //         memo[n] = calcWays(n - 1, memo) + calcWays(n - 2, memo);
    //     return memo[n];
    // }

}
