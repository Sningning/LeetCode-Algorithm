/**
 * 69. x 的平方根
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * https://leetcode-cn.com/problems/sqrtx/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-12 16:13
 */
public class SqrtX_69 {

    public int mySqrt(int x) {

        // 1. 二分
        if (x == 0) {
            return 0;
        }
        long left = 0;
        long right = (x >>> 1) + 1;
        while (left < right) {
            long mid = left + ((right -left + 1) >>> 1);
            long square = mid * mid;
            if (square == x)
                return (int) mid;
            else if (square > x)
                right = mid - 1;
            else if (square < x)
                left = mid;
        }
        return (int) left;


        // 2. 牛顿迭代法
        // long a = x;
        // while ( a * a > x) {
        //     a = (a + x / a) / 2;
        // }
        // return (int) a;
    }
}
