/**
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
 * 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * https://leetcode-cn.com/problems/search-insert-position/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-14 21:48
 */
public class SearchInsertPosition_35 {

    public int searchInsert(int[] nums, int target) {

        // 二分写法 1

        // int left = 0;
        // int right = nums.length - 1;
        // while (left <= right) {
        //     int mid = left + ((right - left) >>> 1);
        //     if (nums[mid] == target)
        //         return mid;
        //     else if (nums[mid] < target)
        //         left = mid + 1;
        //     else if (nums[mid] > target)
        //         right = mid - 1;
        // }
        // return left;


        // 二分写法 2

        int len = nums.length;
        if (nums[len - 1] < target)
            return len;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = left + ((right - left) >>> 1);
            // 当 nums[mid] 严格小于目标元素时，mid 一定不是解
            if (nums[mid] < target)
                // 因为 mid 肯定不是解了
                // 所以下一轮搜索区间 [mid + 1, right]
                left = mid + 1;
            else
                right = mid;
        }
        return left;


    }
}
