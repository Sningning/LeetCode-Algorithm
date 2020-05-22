/**
 * 416. 分割等和子集
 * 给定一个只包含正整数的非空数组。
 * 是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 *
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 *
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-22 8:51
 */
public class PartitionEquaSubsetSum_416 {


    /* 0-1 背包问题
     * 在 n 个物品中选取一定物品，填满 sum/2 的背包
     * F(n,C) 考虑将 n 个物品填满容量为 C 的背包
     * F(i,c) = F(i-1,c) || F(i-1,c-w(i))，返回布尔值，两种方式一种成功即可
     * Time：O(n*sum/2) = O(n*sum)
     */
    static class Solution1 {

        public static boolean canPartition(int[] nums) {

            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            // 特判：如果是奇数，就不符合要求
            if ((sum & 1) == 1)
                return false;

            int C = sum / 2;
            int n = nums.length;

            // 状态定义：dp[i][j]表示从数组的 [0, i] 这个子区间内挑选一些正整数，每个数只能用一次，使得这些数的和恰好等于 j。
            // 状态转移方程是：
            // dp[i][j] = dp[i - 1][j] or dp[i - 1][j - nums[i]]

            // 创建二维状态数组，行：物品索引，列：容量（包括 0）
            boolean[][] dp = new boolean[n][C + 1];

            // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满
            if (nums[0] <= C)
                dp[0][nums[0]] = true;

            // 填充剩下的行
            for (int i = 1; i < n; i++) {
                // 填充第 i 行的每一列
                for (int j = 0; j <= C; j++) {
                    dp[i][j] = dp[i - 1][j];
                    if (j >= nums[i])
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
            return dp[n - 1][C];
        }

        public static void main(String[] args) {
            // int[] nums = {1, 5, 11, 5};
            int[] nums = {1,2,3,4,5,6,7};
            System.out.println(canPartition(nums));  // 预期 true
        }
    }


    /*
     * 用一维数组来优化，从后往前算
     */
    static class Solution2 {

        public static boolean canPartition(int[] nums) {

            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            // 特判：如果是奇数，就不符合要求
            if ((sum & 1) == 1)
                return false;

            int C = sum / 2;
            int n = nums.length;

            // dp[j] 表示当前考虑的背包的容量是否能被填满
            boolean[] dp = new boolean[C + 1];

            // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满
            if (nums[0] <= C)
                dp[nums[0]] = true;

            // i 表示之前表中的行，j 表示当前考虑的背包的容量
            for (int i = 1; i < n; i++) {
                // 填充第 i 行的每一列
                // 从后往前看，如果当前背包容量已经小于nums[i]，那么前面的都不能放置了
                for (int j = C; j >= nums[i]; j--) {
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
            return dp[C];
        }

        public static void main(String[] args) {
            // int[] nums = {1, 5, 11, 5};
            int[] nums = {1,2,3,4,5,6,7};
            System.out.println(canPartition(nums));
        }
    }


}
