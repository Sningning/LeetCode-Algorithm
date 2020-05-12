package array;

/**
 * 26. 删除排序数组中的重复项【简单】
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 *
 * 给定数组 nums = [1,1,2],
 *
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-27 9:13
 */
public class RemoveDuplicates_26 {

    public int removeDuplicates(int[] nums) {

        // 方法：双指针法。时间复杂度O(N)，空间复杂度O(1)
        // 首先注意数组是有序的，那么重复的元素一定会相邻。
        if (nums.length == 0) return 0;
        int i = 0; // i + 1 是下一个元素需要插入的位置
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i ++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
