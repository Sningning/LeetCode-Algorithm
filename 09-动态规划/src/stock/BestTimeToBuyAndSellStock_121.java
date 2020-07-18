package stock;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 *
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *
 * @author: Song Ningning
 * @date: 2020-07-18 23:22
 */
public class BestTimeToBuyAndSellStock_121 {

    /**
     * 暴力枚举
     *
     * 只能买卖一次，可以枚举，找出差值最大的。
     * 由于数组长度达到 10^5，这种方法明显会超时。
     */
    static class Solution1 {
        public int maxProfit(int[] prices) {
            int len = prices.length;
            if (len < 2) {
                return 0;
            }

            // 有可能不做交易，因此结果的初始值设置为 0
            int res = 0;
            for (int i = 0; i < len - 1; i++) {
                for (int j = i + 1; j < len; j++) {
                    res = Math.max(res, prices[j] - prices[i]);
                }
            }
            return res;
        }
    }

    /**
     * 动态规划
     *
     * 两个数组 minPrice[i] 和 maxProfit[i]
     * minPrice[i] 表示前 i 天中的最低股价；maxProfit[i] 表示第 i 天可以获得的最大收益；
     * 状态转移：
     *     minPrice[i] = min(minPrice[i-1], price[i]);
     *     maxProfit[i] = max(maxProfit[i-1], price[i]-minPrice[i-1])，即第 i 天选择不卖或者卖
     */
    static class Solution2 {
        public int maxProfit(int[] prices) {
            int len = prices.length;
            if (len < 2) {
                return 0;
            }
            int[] minPrice = new int[len];
            int[] maxProfit = new int[len];
            minPrice[0] = prices[0];
            maxProfit[0] = 0;
            for (int i = 1; i < len; i++) {
                maxProfit[i] = Math.max(maxProfit[i - 1], prices[i] - minPrice[i - 1]);
                minPrice[i] = Math.min(minPrice[i - 1], prices[i]);
            }
            return maxProfit[len - 1];
        }
    }

    /**
     * 针对方法2进行状态压缩
     * minPrice[i]可以压缩为一个变量；由于 maxProfit[i] 只和前一个状态有关系，所以也可以进行压缩；
     */
    static class Solution3 {
        public int maxProfit(int[] prices) {
            int len = prices.length;
            if (len < 2) {
                return 0;
            }
            int maxProfit = 0;
            // 之前天数中的最低价
            int minPrice = prices[0];
            // 注意从第二天开始
            for (int i = 1; i < prices.length; i++) {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
                minPrice = Math.min(minPrice, prices[i]);
            }
            return maxProfit;
        }
    }


    /**
     * 计算每天的收益
     * prices：[7, 1, 5,  3, 6,  4]
     * gain：    [-6, 4, -2, 3, -2]
     *
     * gain[i] 表示第 i-1 天买入，第 i 天卖出的收益，可以为负数
     * 最终问题转化为求 gain 的子序列的最大值，和力扣第 53 题：最大子序和 相同。
     * 比如：上面的例子中是 [4, -2, 3]，代表第 2 天买入，第 5 天卖出
     *
     * 最后，因为允许不交易，所以最小值肯定是 0，而计算最大连续子序列的和可能为负数，所以最后要取较大值。
     *
     * 时间按复杂度：O(N) + O(N)
     * 空间复杂度：O(N)
     */
    static class Solution4 {
        public int maxProfit(int[] prices) {
            int len = prices.length;
            if (len < 2) {
                return 0;
            }
            int[] gains = new int[len - 1];
            for (int i = 1; i < len; i++) {
                gains[i - 1] = prices[i] - prices[i - 1];
            }
            // 寻找最大连续子序列
            int[] dp = new int[gains.length];
            dp[0] = gains[0];
            for (int i = 1; i < gains.length; i++) {
                if (dp[i - 1] < 0) {
                    dp[i] = gains[i];
                } else { // dp[i - 1] >= 0
                    dp[i] = dp[i - 1] + gains[i];
                }
            }
            // 遍历找到最大值
            int max = dp[0];
            for (int profit : dp) {
                max = Math.max(max, profit);
            }
            return Math.max(max, 0);
        }
    }
}
