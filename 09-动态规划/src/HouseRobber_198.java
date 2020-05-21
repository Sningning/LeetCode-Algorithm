import java.util.Arrays;

/**
 * 198. 打家劫舍
 * 你是一个专业的小偷，计划偷窃沿街的房屋。
 * 每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * https://leetcode-cn.com/problems/house-robber/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-21 10:02
 */
public class HouseRobber_198 {

    /**
     *  暴力递归
     */
    static class Solution1 {

        public int rob(int[] nums) {
            return tryRob(0, nums);
        }

        // 考虑抢劫 nums[index... nums.length) 范围的所有房子---状态
        private int tryRob(int index, int[] nums) {
            if (index >= nums.length)
                return 0;
            int res = 0;
            for (int i = index; i < nums.length; i++) {
                res = Math.max(res, nums[i] + tryRob(i + 2, nums));
            }
            return res;
        }
    }


    /**
     * 记忆化搜索
     */
    static class Solution2 {

        // memo[i] 表示抢劫 nums[i...n) 所能获得的最大收益
        private int[] memo;

        public int rob(int[] nums) {
            memo = new int[nums.length + 1];
            // 因为 0 也可能是一种情况，所以 memo 的初始值不能设置为 0
            Arrays.fill(memo, -1);
            return tryRob(0, nums);
        }

        // 考虑抢劫 nums[index... nums.length) 范围的所有房子---状态
        private int tryRob(int index, int[] nums) {
            if (index >= nums.length)
                return 0;
            if (memo[index] != -1)
                return memo[index];

            int res = 0;
            for (int i = index; i < nums.length; i++) {
                res = Math.max(res, nums[i] + tryRob(i + 2, nums));
            }
            memo[index] = res;
            return res;
        }
    }


    /**
     * 动态规划
     */
    static class Solution3 {
        public int rob(int[] nums) {

            int n = nums.length;
            if (n == 0)
                return 0;

            // dp[i] 表示 考虑 抢劫 nums[i...n - 1] 所能获得的最大收益
            int[] dp = new int[n];
            dp[n - 1] = nums[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                // 求解 dp[i]
                for (int j = i; j < n; j++) {
                    dp[i] = Math.max(dp[i], nums[j] + ( j + 2 < n ? dp[j + 2] : 0));
                }
            }
            return dp[0];
        }
    }


    /**
     * 记忆化搜索---改变状态定义
     */
    static class Solution4 {

        // memo[i] 表示抢劫 nums[0...i] 所能获得的最大收益
        private int[] memo;

        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 0)
                return 0;

            memo = new int[n + 1];
            // 因为 0 也可能是一种情况，所以 memo 的初始值不能设置为 0
            Arrays.fill(memo, -1);
            return tryRob(n, nums);
        }

        // 考虑抢劫 nums[0... index] 范围的所有房子
        private int tryRob(int index, int[] nums) {
            if (index < 0)
                return 0;

            if (memo[index] != -1)
                return memo[index];
            int res = 0;
            for (int i = 0; i <= index; i++) {
                res = Math.max(res, nums[i] + tryRob(i - 2, nums));
            }
            memo[index] = res;
            return res;
        }
    }


    /**
     * 动态规划---改变状态定义
     */
    static class Solution5 {
        public int rob(int[] nums) {
            int n = nums.length;
            if (n == 0)
                return 0;

            // f(k) = 偷 [0..k] 房间中的最大金额
            // f(0) = nums[0]
            // 方案一：不偷 i，结果就是在 [0...i - 1]偷取的最大值
            // 方案二：偷 i，结果就是 i 和 [0...i - 2]能偷取的最大值之和
            // 两种情况中，选择金额较大的一种结果
            // f(k) = max{ rob(k-1), nums[k] + rob(k-2) }

            // dp[i] 表示考虑抢劫 nums[0...i] 所能获得的最大收益
            int[] dp = new int[n];
            dp[0] = nums[0];
            for (int i = 1; i < n; i++) {
                dp[i] = Math.max(dp[i - 1], nums[i] + (i - 2 >= 0 ? dp[i - 2] : 0));
            }
            return dp[n - 1];
        }
    }

}
