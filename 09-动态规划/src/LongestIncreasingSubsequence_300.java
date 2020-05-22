import java.util.Arrays;

/**
 *
 * 300. 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 *
 * 示例:
 *
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 *
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n^2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(nlogn) 吗?
 *
 * https://leetcode-cn.com/problems/longest-increasing-subsequence/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-22 10:14
 */
public class LongestIncreasingSubsequence_300 {

    /*
     * 注意1: 什么是子序列?  必须是原数组中连续的吗？还是不一定连续，只要相符位置不变即可？
     * 注意2: 什么是上升?    严格递增还是可以有重复元素？
     * 注意3: 一个序列可能有多个最长上升子序列;但这个最长的长度只有1个。
     */


    /*
     * 动态规划
     * Time：O(N^2)；Space：O(N)
     *
     * dp(i) 表示[0...i]范围内，选择数字nums[i]可以获得的最长上升子序列长度
     * 即表示以 nums[i] 结尾的「上升子序列」的长度。注意：这个定义中 nums[i] 必须被选取，且必须是这个子序列的最后一个元素。
     * dp[i] = max(1 + dp[j] if nums[i] > nums[j])
     *
     * 最后要扫描 dp 才能确定最长序列长度,dp[n]是以 n 结尾的结果，但是不一定最长
     */
    static class Solution1 {

        public static int lengthOfLIS(int[] nums) {
            int n = nums.length;
            if (n == 0)
                return 0;

            // dp[i]表示以 nums[i]为结尾的最长上升子序列的长度
            int[] dp = new int[n];
            // 初始化
            Arrays.fill(dp, 1);

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i]) {
                        dp[i] = Math.max(dp[i], 1 + dp[j]);
                    }
                }
            }

            int res = 1;
            for (int num : dp) {
                res = Math.max(num, res);
            }
            return res;
        }


        public static void main(String[] args) {
            int[] nums1 = {10,9,2,5,3,7,101,18};
            System.out.println(lengthOfLIS(nums1));  // 4

            int[] nums2 = {10,15,20,11,9,101};
            System.out.println(lengthOfLIS(nums2)); // 4

            int[] nums3 = {1, 3, 6, 7, 9, 4, 10, 5, 6};
            System.out.println(lengthOfLIS(nums3)); // 6
        }
    }

}
