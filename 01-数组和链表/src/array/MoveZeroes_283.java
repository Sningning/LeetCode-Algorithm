package array;

/**
 * 283. 移动零【简单】
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 *
 * 示例:
 *
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 *
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * https://leetcode-cn.com/problems/move-zeroes/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-25 18:28
 */
public class MoveZeroes_283 {

    public void moveZeroes(int[] nums) {

        // 方法1. 开一个和nums一样长的新数组，设置两个指针，一个在头，一个在尾，遍历nums，遇到非零往前放，遇到零往后放。
        // 空间复杂度：O(N)。

        // int[] help = new int[nums.length];
        // int head = 0;
        // int tail = help.length - 1;
        // for (int i = 0; i < nums.length; i++) {
        //     if (nums[i] != 0) {
        //         help[head++] = nums[i];
        //     } else {
        //         help[tail--] = nums[i];
        //     }
        // }
        // for (int i = 0; i < help.length; i++) {
        //     nums[i] = help[i];
        // }


        // 方法2. 一层for循环，直接进行非零数值移动，后尾补零
        int index = 0;  // 非零元素待插入的位置
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != 0) {
                nums[index] = nums[i];
                if (index != i) {
                    nums[i] = 0;
                }
                index++;
            }
        }


        // int j = 0;  // 非零元素待插入的位置
        // for (int i = 0; i < nums.length; i++) {
        //     if (nums[i] != 0) {
        //         nums[j] = nums[i];
        //         j++;
        //     }
        // }
        // while (j != nums.length) {  // 将剩下的全部赋值为零
        //     nums[j++] = 0;
        // }


        // int j = 0;  // 非零元素待插入的位置
        // for (int i = 0; i < nums.length; i++) {
        //     if (nums[i] != 0) {
        //         int temp = nums[j];
        //         nums[j] = nums[i];
        //         nums[i] = temp;
        //         j++;
        //     }
        // }

    }

}
