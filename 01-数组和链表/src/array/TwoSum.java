package array;

import java.util.HashMap;

/**
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * https://leetcode-cn.com/problems/two-sum/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-25 23:44
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {

        // 1. 暴力解法
        // 时间复杂度O(N^2)，空间复杂度O(1)

        // int[] res = new int[2];
        // for (int i = 0; i < nums.length - 1; i++) {
        //     for (int j = i + 1; j < nums.length; j++) {
        //         if (nums[i] + nums[j] == target) {
        //             res[0] = i;
        //             res[1] = j;
        //             return res;
        //         }
        //     }
        // }
        // return new int[0];


        // 2. 哈希表。
        // 由于哈希查找的时间复杂度为 O(1)，所以可以利用哈希容器 map 降低时间复杂度
        // 遍历数组 nums，i 为当前下标，每个值都判断map中是否存在 target-nums[i] 的 key 值
        // 如果存在则找到了两个值，如果不存在则将当前的 (nums[i],i) 存入 map 中，继续遍历直到找到为止

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];

    }

}
