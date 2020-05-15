/**
 * 81. 搜索旋转排序数组 II
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 *
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 *
 * https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-15 19:30
 */
public class SearchInRotatedSortedArray2_81 {

    public boolean search(int[] nums, int target) {

        int len = nums.length;
        if (len == 0)
            return false;

        int left = 0;
        int right = len - 1;
        while (left <= right) {
            int mid = left + ((right - left) >>> 1);
            // 直接命中
            if (nums[mid] == target)
                return true;
            // 10111 和 11101 这种。
            // 此种情况下 nums[left] == nums[mid]，分不清到底是前面有序还是后面有序，
            // 此时 left ++ 即可。相当于去掉一个重复的干扰项。
            if (nums[mid] == nums[left])
                left++;

            else if (nums[mid] > nums[left]) {
                // 如果 nums[mid] > nums[left]，[left, mid] 一定有序
                if (nums[left] <= target && target < nums[mid])
                    // target 在前半部分
                    right = mid - 1;
                else
                    left = mid + 1;
            }
            else if (nums[mid] < nums[left]) {
                // 如果 nums[mid] < nums[left]，[mid, right] 一定有序
                if (nums[mid] < target && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return false;
    }
}
