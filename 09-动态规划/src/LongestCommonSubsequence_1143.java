/**
 * 1143. 最长公共子序列
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 *
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 *
 * 若这两个字符串没有公共子序列，则返回 0。
 *
 * 提示:
 *
 * 1 <= text1.length <= 1000
 * 1 <= text2.length <= 1000
 * 输入的字符串只含有小写英文字符。
 *
 * @Author: Song Ningning
 * @Date: 2020-05-25 10:35
 */
public class LongestCommonSubsequence_1143 {

    /*
     * dp[m][n] 表示 text1[0,m] 和 text2[0,n] 的最长公共子序列的长度
     * 两种情况：
     * 1. text1[m] == text2[n]：dp[m][n] = 1 + dp[m-1][n-1]
     * 2. text1[m] != text2[n]：dp[m][n] = max(dp[m-1][n], dp[m][n-1])
     */

    static class Solution1 {

        public int longestCommonSubsequence(String text1, String text2) {

            int m = text1.length();
            int n = text2.length();
            if (m == 0 || n == 0) {
                return 0;
            }

            // 构建 DP table
            int[][] dp = new int[m][n];
            char[] ch1 = text1.toCharArray();
            char[] ch2 = text2.toCharArray();

            // 初始化
            // 初始化第 0 行
            for (int j = 0; j < n; j++) {
                if (ch1[0] == ch2[j]) {
                    for (int k = j; k < n; k++) {
                        dp[0][k] = 1;
                    }
                    break;
                }
            }
            // 初始化第 0 列
            for (int i = 0; i < m; i++) {
                if (ch2[0] == ch1[i]) {
                    for (int k = i; k < m; k++) {
                        dp[k][0] = 1;
                    }
                    break;
                }
            }

            // 动态规划的过程
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (ch1[i] == ch2[j]) {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }

            return dp[m - 1][n - 1];

            /*
            // 通过 dp 反向求解两个串的最长公共子序列
            m = text1.length() - 1;
            n = text2.length() - 1;
            StringBuilder res = new StringBuilder();
            while (m >= 0 && n >= 0) {
                if (ch1[m] == ch2[n]) {
                    res.insert(0, ch1[m]);
                    m--;
                    n--;
                } else if (m == 0)
                    n--;
                else if (n == 0)
                    m--;
                else {
                    if (dp[m - 1][n] > dp[m][n - 1])
                        m--;
                    else
                        n--;
                }
            }
            return res.toString();
            */
        }
    }

    static class Solution2 {

        // 第二种写法
        // dp 表增加一行一列，用来表示空串，建完表也完成了初始化

        public int longestCommonSubsequence(String text1, String text2) {
            int m = text1.length();
            int n = text2.length();

            // 构建 DP table 同时完成初始化
            int[][] dp = new int[m + 1][n + 1];
            char[] ch1 = text1.toCharArray();
            char[] ch2 = text2.toCharArray();

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    // 如果末端相同
                    if (ch1[i - 1] == ch2[j - 1]) {
                        dp[i][j] = 1 + dp[i - 1][j - 1];
                    } else {  // 如果末端不同
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[m][n];
        }
    }
}