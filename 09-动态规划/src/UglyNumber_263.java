/**
 * 263. 丑数
 * 编写一个程序判断给定的数是否为丑数。
 * 丑数就是只包含质因数 2, 3, 5 的正整数。
 * 
 * 示例 1:
 * 输入: 6
 * 输出: true
 * 解释: 6 = 2 × 3
 * 
 * 示例 2: 
 * 输入: 8
 * 输出: true
 * 解释: 8 = 2 × 2 × 2
 * 
 * 示例 3:
 * 输入: 14
 * 输出: false 
 * 解释: 14 不是丑数，因为它包含了另外一个质因数 7。
 * 
 * 说明：
 *  1 是丑数。
 * 输入不会超过 32 位有符号整数的范围: [−231,  231 − 1]。
 * 
 * https://leetcode-cn.com/problems/ugly-number/
 * 
 * @Author: Song Ningning
 * @Date: 2020-06-28 20:17
 * 
 */
public class UglyNumber_263 {
    
    /**
     * 丑数只包含质因数 2, 3, 5
     * 因此判断一个数 num 是不是丑数，可以将 num 先一直除以 2，直到除不尽，再将 num 一直除以 3，直到除不尽，
     * 再将 num 一直除以 5，直到除不尽；如果最后的 num 为 1，则该数是丑数，否则不是丑数。
     */
    public boolean isUgly(int num) {
        if (num <= 0) return false;
        while (num % 2 == 0) num /= 2;
        while (num % 3 == 0) num /= 3;
        while (num % 5 == 0) num /= 5;
        return num == 1;
    }
}