/**
 * 367. 有效的完全平方数
 * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 *
 * https://leetcode-cn.com/problems/valid-perfect-square/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-12 17:39
 */
public class ValidPerfectSquare_367 {

    public static boolean isPerfectSquare(int num) {

        // 1. 二分

        long left = 0;
        long right = (num >>> 1) + 1;
        while (left <= right) {
            long mid = (left + right) >>> 1;
            long square = mid * mid;
            if (square == num) {
                return true;
            } else if (square < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;


        // 2. 牛顿迭代法

        // if (num < 2) {
        //     return true;
        // }
        //
        // long x = num >>> 1;
        // while (x * x > num) {
        //     x = (x + num / x) >>> 1;
        // }
        // return x * x == num;
    }

    public static void main(String[] args) {
        System.out.println(isPerfectSquare(16));
        System.out.println(isPerfectSquare(14));
    }
}
