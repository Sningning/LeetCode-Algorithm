/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。
 * 找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-14 23:14
 */
public class FindFirstAndLastPositionOfElementInSortedArray_34 {

    public int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        if (len == 0 || target < nums[0] || target > nums[len - 1])
            return new int[]{-1, -1};
        int first = findFirstPosition(nums, target, len);
        if (first == -1) {
            return new int[]{-1, -1};
        }

        int last = findLastPosition(nums, target, len);
        return new int[]{first, last};
    }


    private int findFirstPosition(int[] nums, int target, int len) {
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = left + ((right - left) >>> 1);
            if (nums[mid] < target) {
                // 如果 nums[mid] 严格小于 target，
                // 那么在 mid 位置以及 mid 之前的位置一定不存在目标元素
                // 下一轮搜索区间 [mid + 1, right]
                left = mid + 1;
            }
            else {
                // nums[mid] >= target
                // 下一轮搜索区间 [left, mid]
                // mid 存在的话一定存在于 [left, mid] 区间
                // 因为一直是 right 在收缩，所以找到第一个出现的位置
                right = mid;
            }
        }
        // target 可能不存在于数组中，需要进行判断
        if (nums[left] == target) {
            return left;
        }
        return -1;
    }


    private int findLastPosition(int[] nums, int target, int len) {
        int left = 0;
        int right = len - 1;
        while (left < right) {
            // 注意
            int mid = left + ((right - left + 1) >>> 1);
            if (nums[mid] > target) {
                // 如果 nums[mid] 严格大于 target，
                // 那么在 mid 位置以及 mid 之后的位置一定不存在目标元素
                // 下一轮搜索区间 [left, mid - 1]
                right = mid - 1;
            }
            else {
                // nums[mid] <= target
                // 下一轮搜索区间 [mid, right]
                // mid 存在的话一定存在于 [mid, right] 区间
                // 因为一直是 left 在收缩，所以找到最后出现的位置
                // 因为出现了 left = mid，所以应该取右中位数
                left = mid;
            }
        }
        // 主函数中先调用了 findFirstPosition，如果可以执行到该函数，
        // 说明 target 在数组中一定存在，不需要再额外判断
        return left;
    }


}
