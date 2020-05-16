/**
 * 704. 二分查找
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
 * 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 *
 * https://leetcode-cn.com/problems/binary-search/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-14 22:26
 */
public class BinarySearch_704 {

    public int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >>> 1);
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1;
        }
        return -1;


        // int right = nums.length - 1;
        // while (left < right) {
        //     int mid = left + ((right - left) >>> 1);
        //     if (nums[mid] == target)
        //         return mid;
        //     if (nums[mid] < target)
        //         left = mid + 1;
        //     else
        //         right = mid - 1;
        // }
        // return nums[left] == target ? left : -1;
    }
}
