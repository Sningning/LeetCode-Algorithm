/**
 * 33. 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-12 18:52
 */
public class SearchInRotatedSortedArray_33 {

    static class Solution1{
        public int search(int[] nums, int target) {

            // 1. 暴力。但是不符合 复杂度必须是 O(log n) 要求

            // for (int i = 0; i < nums.length; i++) {
            //     if (nums[i] == target)
            //         return i;
            // }
            // return -1;


            // 2. 二分
            // 很容易能够根据 mid 索引的位置和第 1 个索引的位置（或者最后一个索引的位置）判断 mid 落在哪个有序的区间里。
            // 因此 mid 前后必定有一部分是有序的

            int len = nums.length;
            if (len == 0)
                return -1;
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int mid = left + ((right - left) >>> 1);
                if (nums[0] < nums[mid]) {
                    // [left, mid] 严格有序
                    if (nums[0] <= target && target <= nums[mid]) {
                        right = mid;
                    } else {
                        left = mid + 1;
                    }
                } else {
                    // 因为没有重复元素，所以是严格大于
                    // nums[0] > nums[mid]
                    // [mid, right] 严格有序
                    // 因为 left 的更新是 left = mid, 所以中位数应该是右中位数
                    // 但为了统一，所以使用 nums[mid+1], left = mid + 1; right = mid
                    if (nums[mid + 1] <= target && target <= nums[right]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
            }
            return nums[left] == target ? left : -1;
        }
    }


    static class Solution2 {
        public int search(int[] nums, int target) {
            if (nums.length == 0)
                return -1;
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = left + ((right - left) >>> 1);
                if (nums[mid] == target)
                    return mid;
                // [left, mid] 连续递增
                // 这里取等号是考虑到只剩下两个数的时候
                if (nums[left] <= nums[mid]) {
                    if (nums[left] <= target && target < nums[mid])
                        right = mid - 1;
                    else
                        left = mid + 1;
                }
                // [mid, right] 连续递增
                else {
                    if (nums[mid] < target && target <= nums[right])
                        left = mid + 1;
                    else
                        right = mid - 1;
                }
            }
            return -1;
        }
    }
}
