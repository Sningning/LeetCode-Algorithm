/**
 * 50. Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 *
 * 示例 1:
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 *
 * 示例 2:
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2^(-2) = 1/(2^2) = 1/4 = 0.25
 *
 * 说明:
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 *
 * https://leetcode-cn.com/problems/powx-n/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-05 11:51
 */
public class PowXN_50 {

    public double myPow(double x, int n) {

        // 1. 暴力： 超时
        // 时间：O(N)；空间：O(1)

        // long N = n;
        // if (n < 0) {
        //     x = 1 / x;
        //     N = -N;
        // }
        // double res = 1.0;
        // for (long i = 0; i < N; i++) {
        //     res *= x;
        // }
        // return res;


        // 2. 快速幂算法（递归）
        // 时间：O(logN)；空间：O(logN)
        int N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return fastPow(x, N);

    }

    private double fastPow(double x, int n) {

        if (n == 0) return 1.0;

        double subRes = fastPow(x, n / 2);

        return n % 2 == 0 ? subRes * subRes : subRes * subRes * x;
    }


    // for test
    public static void main(String[] args) {

        PowXN_50 pow = new PowXN_50();
        System.out.println(pow.myPow(1.0, 2147483647));
        System.out.println(pow.myPow(2.0, -2147483648));

    }
}
