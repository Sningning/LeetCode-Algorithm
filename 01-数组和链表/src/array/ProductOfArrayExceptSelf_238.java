package array;

import java.util.Arrays;

/**
 * 238. 除自身以外数组的乘积
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 * 
 * 示例:
 * 输入: [1,2,3,4]
 * 输出: [24,12,8,6]
 * 
 * 提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
 * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * 
 * 进阶：
 * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 * https://leetcode-cn.com/problems/product-of-array-except-self/
 *
 * @Author: Song Ningning
 * @Date: 2020-06-04 10:18
 */

 public class ProductOfArrayExceptSelf_238 {

    /**
     * 利用两个数组 left 和 right，left[i] 表示 i 位置左侧所有数字的乘积；right[i] 表示 i 位置右侧所有数字的乘积
     * 最后令 left[i] = left[i] * right[i]，返回 left
     * 
     * Time：O(N)，3 轮循环
     * Space：O(N)，返回数组不视为额外空间，相当于只使用了一个 right 数组
     */
    static class Solution1 {

        public static int[] productExceptSelf(int[] nums) {
            int[] left = new int[nums.length];
            int[] right = new int[nums.length];
            Arrays.fill(left, 1);
            Arrays.fill(right, 1);
            // 计算 i 位置左边所有数的乘积
            for (int i = 1; i < nums.length; i++)
                left[i] = left[i - 1] * nums[i - 1];
    
            // 计算 i 位置右边所有数的乘积
            for (int i = nums.length - 2; i >= 0; i--)
                right[i] = nums[i + 1] * right[i + 1];
            
            // 计算 i 位置的左右乘积
            for (int i = 0; i < nums.length; i++) 
                left[i] *= right[i];
    
            return left;
        }

        public static void main(String[] args) {
            int[] nums = {1,2,3,4};
            int[] res = productExceptSelf(nums);
            for (int num : res)
                System.out.print(num + " ");  // [24,12,8,6]
        }
    }


    /**
     * 先从左边开始一遍循环计算出 i 位置的左侧数字的乘积，存入 res 数组；
     * 用变量 r 记录 i 位置右侧所有数字的乘积，然后乘上 res[i] 后更新 res[i]
     * 所以 r 从最右边开始，初始值为 1
     * 
     * Time：O(N)，遍历两遍数组
     * Space：O(1)，返回数组不视为额外空间，只使用了一个变量 r
     */
    static class Solution2 {

        public static int[] productExceptSelf(int[] nums) {

            int[] res = new int[nums.length];
            int r = 1;
            Arrays.fill(res, 1);
            // 计算 i 位置左边所有数的乘积
            for (int i = 1; i < res.length; i++) 
                res[i] = res[i - 1] * nums[i - 1];

            // 计算 i 位置的左右乘积
            for (int i = nums.length - 1; i >= 0; i--) {
                res[i] = res[i] * r;
                r *= nums[i];
            }
            return res;
        }

        public static void main(String[] args) {
            int[] nums = {1,2,3,4};
            int[] res = productExceptSelf(nums);
            for (int num : res)
                System.out.print(num + " ");  // [24,12,8,6]
        }
    }
 }