import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 239. 滑动窗口最大值【困难】
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-28 15:25
 */
public class SlidingWindowMaximum_239 {

    public int[] maxSlidingWindow(int[] nums, int k) {

        // // 方法一：暴力
        //
        // // 遍历每个滑动窗口，找到每个窗口的最大值。一共有 N - k + 1 个滑动窗口，每个有 k 个元素
        // // 时间复杂度：O(Nk)；空间复杂度：O(N - k + 1)，输出数组的长度。
        //
        // int len = nums.length;
        // if (len == 0 || k == 0)  return new int[0];
        // int[] res = new int[len - k + 1];
        // for (int i = 0; i < len - k + 1; i++) {
        //     int maxValue = Integer.MIN_VALUE;
        //     for (int j = i; j < i + k; j++) {
        //         maxValue = Math.max(maxValue, nums[j]);
        //     }
        //     res[i] = maxValue;
        // }
        // return res;



        // 方法二：双端队列 / 单调队列

        // 该队列的特点：
        // ① 队列的最前端是此次遍历的最大值的下标
        // ② 当我们遇到新的数时，将新的数和双项队列的末尾比较：
        //      如果末尾比新数小，那么这个末尾肯定没有可能成为最大，把末尾扔掉，直到该队列的末尾比新数大或者队列为空的时候才停止，再把新数加到尾部
        //      如果末尾比新数大，新数直接加到队尾，虽然小，但是存起来的原因就是当窗口滑动到，前面几个都不在范围内了，这个数就有可能是最大的
        // ③ 双项队列中的所有值都要在窗口范围内

        if (nums == null || nums.length < 2)  return nums;

        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
        Deque<Integer> queue = new ArrayDeque<>();
        // 结果数组
        int[] res = new int[nums.length - k + 1];
        // 遍历nums数组
        for (int i = 0; i < nums.length; i++) {
            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            // 添加当前值对应的数组下标
            queue.addLast(i);
            // 判断当前队列中队首的值是否有效
            // 如果队首元素不在范围内了，出队
            if (queue.peekFirst() <= i - k) {
                queue.pollFirst();
            }
            // 开始 [0, k - 2]范围还没有达到 k，所以不记录，当 i == k - 1 时开始记录
            // 当窗口长度为k时 保存当前窗口中最大值
            if (i + 1 >= k) {
                res[i - k + 1] = nums[queue.peekFirst()];
            }
        }
        return res;
    }

}
