import java.util.HashMap;

/**
 * 9. 回文数
 * 判断一个整数是否是回文数。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 * 输入: 121
 * 输出: true
 *
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 进阶:
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 * https://leetcode-cn.com/problems/palindrome-number/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-11 0:17
 */
public class PalindromeNumber_9 {

    static class Solution1 {

        public boolean isPalindrome(int x) {
            if (x >= 0 && x < 10)
                return true;
            if (x < 0 || (x % 10 == 0))
                return false;
            String s = String.valueOf(x);
            int p1 = 0;
            int p2 = s.length() - 1;
            for (; p1 < p2; p1++, p2--)
                if (s.charAt(p1) != s.charAt(p2)) return false;
            return true;
        }
    }

    /**
     * 也可以不转换成字符串，直接翻转数字，但是，当数字很大时，比如 2...999，翻转后变成 999...2，可能会越界
     * 因此考虑从个位数字一位一位的翻转，翻转一位，就进行比较。
     * 比如：123321
     * (123321, 0) --> (12332, 1) --> (1233, 12) --> (123, 123) --> 相等，是回文数
     * 但是如果是奇数位：1234321
     * (1234321, 0) --> (123432, 1) --> (12343, 12) --> (1234, 123)  --> ...
     * 那是不是在 while 循环中判断语句写成 if(a==b || a/10==b) 就可以了呢？
     * 考虑 10，a=1 b=0，a/10 == b，可 10 并不是回文数，其实数字 0 结尾的数字其实都不是回文数
     */
    static class Solution2 {
        public static boolean isPalindrome(int x) {
            // 如果只有一位，肯定是回文数
            if (x >= 0 && x < 10) return true;
            // 如果小于 0 或者个位数是 0，肯定不是回文
            if (x < 0 || (x % 10 == 0)) return false;
            int reverse = 0;
            while (x > 0) {
                reverse *= 10;
                reverse += (x % 10);
                x /= 10;
                if (x == reverse || (x / 10 == reverse))
                    return true;
            }
            return false;
        }
    }

    /**
     * 为了避免太多的特殊情况。比如只有一位数的情况，可以把 while 循环继续的条件改为 x > reverse
     * 其实在翻转过程中，x 是一直减小的，reverse 是一直增大的；
     *     当 x == reverse 时，说明 x 是偶数位，是回文数；
     *     当 x < reverse 时，说明 x 是奇数位，此时 reverse 比 x 多一位，去掉 x 的个位数，剩下数如果等于 x，说明也是回文数。
     */
    static class Solution3 {
        public static boolean isPalindrome(int x) {

            // 如果小于 0 或者个位数是 0（不包括x=0），肯定不是回文
            if (x < 0 || (x % 10 == 0 && x != 0)) return false;
            int reverse = 0;
            while (x > reverse) {
                reverse *= 10;
                reverse += (x % 10);
                x /= 10;
            }
            return x == reverse || (x == reverse / 10);
        }
    }
}
