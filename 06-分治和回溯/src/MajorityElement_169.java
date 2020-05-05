/**
 * TODO 分治解法
 *
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * https://leetcode-cn.com/problems/majority-element/description/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-05 15:55
 */
public class MajorityElement_169 {

    public int majorityElement(int[] nums) {

        // 1. 哈希表。时间：O(N)；空间：O(N)

        // Map<Integer, Integer> map = new HashMap<>();
        // for (int num : nums) {
        //
        //     if (map.containsKey(num)) {
        //         map.put(num, map.get(num) + 1);
        //     } else {
        //         map.put(num, 1);
        //     }
        //     if (map.get(num) > nums.length / 2) {
        //         return num;
        //     }
        // }
        // return 0;


        // 2. 排序。时间：O(NlogN)；空间：O(logN)
        // 将 nums 中所有元素单调排序，下标为 n / 2 的元素一定是众数。

        // Arrays.sort(nums);
        // return nums[nums.length / 2];


        // 3. 摩尔投票法。时间：O(N)；空间：O(1)
        // 投票法是遇到相同的则票数 + 1，遇到不同的则票数 - 1。
        // 且“多数元素”的个数 > [n/2]，其余元素的个数总和 <= [n/2]。
        // 因此“多数元素”的个数 - 其余元素的个数总和 的结果 肯定 >= 1。
        // 这就相当于每个“多数元素”和其他元素 两两相互抵消，抵消到最后肯定还剩余至少 1 个“多数元素”。

        int candidate = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (candidate == nums[i]) {
                count ++;
            } else {
                count--;
            }
        }
        return candidate;


        // 4. 分治

    }
}
