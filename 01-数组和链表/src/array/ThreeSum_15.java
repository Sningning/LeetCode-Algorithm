package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 三数之和【中等】
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 *
 * https://leetcode-cn.com/problems/3sum/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-25 23:35
 */
public class ThreeSum_15 {

    // 问题可以转换为 a + b = -c (target)

    public List<List<Integer>> threeSum(int[] nums) {


        // 1. 暴力，三重循环，时间复杂度O(N^3)，超时
        // 没要求返回下标，而且不可以包含重复的三元组，先排序,然后用 set 存
        // Arrays.sort(nums);
        // Set<List<Integer>> res = new LinkedHashSet<>();
        // for (int i = 0; i < nums.length - 2; i++) {
        //     for (int j = i + 1; j < nums.length - 1; j++) {
        //         for (int k = j + 1; k < nums.length; k++) {
        //             if (nums[i] + nums[j] + nums[k] == 0) {
        //                 List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k]);
        //                 res.add(list);
        //             }
        //         }
        //     }
        // }
        // return new ArrayList<>(res);


        // 2. hash：两重暴力+hash



        // 3. 双指针左右夹逼
        // 固定 3 个指针中最左（最小）数字的指针 i，双指针 lo，hi 分设在数组索引 (i, len(nums)) 两端，即 i 右侧部分，
        // 通过双指针交替向中间移动，记录对于每个固定指针 i 的所有满足 nums[i] + nums[lo] + nums[hi] == 0 的 lo,hi 组合
        // 本题的难点在于如何去除重复解。
        // 流程：
        // 1）对数组进行排序。
        // 2）遍历排序后数组：令 sum = nums[i] + nums[lo] + nums[hi]
        //  ① 如果 num[i] > 0，由于 nums 已经有序，因此右侧都比 nums[i] 大，直接返回当前结果
        //  ② 如果 nums[i] == nums[i-1]，则说明该数字重复，之前已经考虑过，会导致结果重复，所以应该跳过
        //  ③ sum == 0 时：
        //      如果 nums[lo] == nums[lo + 1]，为了避免重复，应该令 lo++，直到nums[lo] != nums[lo + 1]，再 lo++，到达下一个该判断的位置
        //      如果 nums[hi] == nums[hi - 1]，为了避免重复，应该令 hi--，直到nums[hi] != nums[hi - 1]，再 hi--，到达下一个该判断的位置

        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (nums[i] > 0) {
                return res;
            }
            // 注意这里不能写成 if (nums[i] == nums[i + 1]) continue;
            // 比如 -1 -1 2 这种情况使用上面条件就会跳过该解
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;  // 去重
            }
            int lo = i + 1;
            int hi = nums.length - 1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    while (lo < hi && nums[lo] == nums[lo + 1])  lo ++;
                    while (lo < hi && nums[hi] == nums[hi - 1])  hi --;
                    lo ++;
                    hi --;
                } else if (sum < 0) {
                    lo ++;
                } else {  // sum > 0
                    hi --;
                }
            }
        }
        return res;
    }

}
