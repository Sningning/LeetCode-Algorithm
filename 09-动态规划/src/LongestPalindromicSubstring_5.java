/**
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * https://leetcode-cn.com/problems/longest-palindromic-substring/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-21 23:35
 */
public class LongestPalindromicSubstring_5 {

    // 暴力搜索
    // Time：O(N^3)---两层循环+判断回文；Space：O(1)
    static class Solution1 {

        public String longestPalindrome(String s) {
            int n = s.length();
            if (n < 2)
                return s;

            int maxLen = 1;
            int start = 0;
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if ((j - i + 1) > maxLen && isPalindromic(s, i, j)) {
                        maxLen = j - i + 1;
                        start = i;
                    }
                }
            }
            return s.substring(start, start + maxLen);

        }

        private boolean isPalindromic(String s, int left, int right) {
            if (left < 0 || right >= s.length())
                return false;

            while (left < right) {
                if (s.charAt(left) != s.charAt(right))
                    return false;
                left++;
                right--;
            }
            return true;
        }

        public static void main(String[] args) {
            // String s = "babad";
            String s = "cbbd";
            Solution1 solution1 = new Solution1();
            System.out.println(solution1.longestPalindrome(s));
        }
    }


    static class Solution2 {

        // 中心扩散法
        // 枚举可能出现的回文子串的“中心位置”，从“中心位置”尝试尽可能扩散出去，得到一个回文串。
        // Time：O(N^2)---枚举每个位置+从中心扩散；Space：O(1)

        // 注意：回文串在长度为奇数和偶数的时候，“回文中心”的形式是不一样的
        public String longestPalindrome(String s) {
            if (s.length() < 2)
                return s;

            int maxLen = 1;
            String res = "";
            for (int i = 0; i < s.length() - 1; i++) {
                String oddStr = centerSpread(s, i, i);
                String evenStr = centerSpread(s, i, i + 1);
                String maxLenStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;
                if (maxLenStr.length() > maxLen) {
                    maxLen = maxLenStr.length();
                    res = maxLenStr;
                }
            }
            return res;
        }

        private String centerSpread(String s, int left, int right) {
            // left = right 的时候，此时回文中心是一个字符，回文串的长度是奇数
            // right = left + 1 的时候，此时回文中心是一个空隙，回文串的长度是偶数
            int i = left;
            int j = right;
            char[] ch = s.toCharArray();
            while (i >= 0 && j < s.length()) {
                if (ch[i] == ch[j]) {
                    i--;
                    j++;
                } else {
                    break;
                }
            }
            // 跳出 while 循环时，恰好满足 ch[i] != ch[j]，因此不能取 i，不能取 j
            return s.substring(i + 1, j);
        }
    }



    // 动态规划
    static class Solution3 {

        public String longestPalindrome(String s) {

            /*
             * 在头尾字符相等的情况下，里面子串的回文性质据定了整个子串的回文性质.
             * 定义状态：dp[i][j] 表示子串 s[i..j] 是否为回文子串
             * 考虑初始化：单个字符是回文，因此 dp 表对角线上为 true (实际上可以不初始化，因为对角线上的状态并不会被参考)
             *           由于 i < j，因此只需填写 dp 表对角线以上部分
             * 状态转移：
             *   s[i] == s[j]：if dp[i+1][j-1] true -> dp[i][j] true;
             *                 if dp[i+1][j-1] false -> dp[i][j] false;
             *                 还有种情况是，当如果 [i+1...j-1] 总共包含的元素不足 2 个，不用转移了，直接为 true, 
             *                 因为空串和 1 个字符一定是回文串，即 (j-1) - (i+1) + 1 < 2 --> j - i < 3，
             *                 也即 s[i...j] 的长度为 1、2、3 时就直接置为 true
             *   s[i] != s[j] dp[i][j] false;
             * 在填表的过程中，每次只参考了【左下方】的数值，所以先填列，再填行
             *
             * 输出：得到 dp[i][j] == true 时，记录字符串的起始位置和对应的长度，最后根据这两个信息截取子串
             *
             *
             *
             */
            int n = s.length();
            if (n < 2)
                return s;
            
            boolean [][] dp = new boolean[n][n];

            for (int i = 0; i < n; i++) 
                dp[i][i] = true;

            int maxLen = 1;
            int start = 0;
            char[] ch = s.toCharArray();

            // 选择先填列
            for (int j = 1; j < n; j++) {
                for (int i = 0; i < j; i++) {
                    if (ch[i] != ch[j]) {
                        dp[i][j] = false;
                    } else {  // ch[j] == ch[i]
                        if (j - i < 3)
                            dp[i][j] = true;
                        else
                            dp[i][j] = dp[i + 1][j - 1];
                    }
                    if (dp[i][j] && (j - i + 1) > maxLen) {
                        maxLen = j - i + 1;
                        start = i;
                    }
                }
            }
            return s.substring(start, start + maxLen);
        }
    }

}
