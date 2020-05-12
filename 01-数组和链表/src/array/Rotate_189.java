package array;

/**
 * 189. 旋转数组【简单】
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 * https://leetcode-cn.com/problems/rotate-array/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-27 9:36
 */
public class Rotate_189 {

    public void rotate(int[] nums, int k) {

        // 方法 1 ：暴力。时间复杂度O(N * k)，空间复杂度O(1)
        // 最简单的方法是旋转 k 次，每次将数组旋转 1 个元素。

        // int cur = 0;  // 保存当前需要放置的元素
        // int temp = 0; // 保存待放入位置之前的元素
        // for (int i = 0; i < k; i++) {
        //     cur = nums[nums.length - 1];
        //     for (int j = 0; j < nums.length; j++) {
        //         temp = nums[j];
        //         nums[j] = cur;
        //         cur = temp;
        //     }
        // }



        // 方法 2 ：开辟新数组。时间复杂度O(N)，空间复杂度O(N)
        // 将旧数组中元素放到正确位置，即 aux[(i + k) % nums.length] = nums[i]

        // int[] aux = new int[nums.length];
        // for (int i = 0; i < nums.length; i++) {
        //     aux[(i + k) % nums.length] = nums[i];
        // }
        // for (int i = 0; i < aux.length; i++) {
        //     nums[i] = aux[i];
        // }



        // 方法 3 ：使用反转
        // 这个方法基于这个事实：当旋转数组 k 次， k % n 个尾部元素会被移动到头部，剩下的元素会被向后移动。
        // 首先将所有元素反转。然后反转前 k 个元素，再反转后面 n-k 个元素，就能得到想要的结果。
        //
        // 假设 n=7 且 k=3。
        //
        // 原始数组                  : 1 2 3 4 5 6 7
        // 反转所有数字后             : 7 6 5 4 3 2 1
        // 反转前 k 个数字后          : 5 6 7 4 3 2 1
        // 反转后 n-k 个数字后        : 5 6 7 1 2 3 4 --> 结果

        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);

    }

    // 将数组 nums 中 [l...r] 范围元素翻转（其实就是前后交换）
    private void reverse(int[] nums, int l, int r) {
        int temp = 0;
        while (l < r) {
            temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l ++;
            r --;
        }
    }


}
