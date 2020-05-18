/**
 * 153. 寻找旋转排序数组中的最小值
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 请找出其中最小的元素。
 *
 * 你可以假设数组中不存在重复元素。
 *
 * https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-18 18:21
 */
public class FindMinimumInRotatedSortedArray2_154 {

    public int findMin(int[] nums) {

        // 暴力
        // int res = Integer.MAX_VALUE;
        // for (int number : nums)
        //     res = Math.min(res, number);
        // return res;

        // 二分
        int len = nums.length;
        if (len == 0)
            return 0;
        int left = 0;
        int right = len - 1;
        while (left < right) {
            int mid = left + ((right - left) >>> 1);
            if (nums[mid] < nums[right]) right = mid;
            else if (nums[mid] > nums[right]) left = mid + 1;
            else if (nums[mid] == nums[right]) right--;
        }
        return nums[left];
    }
}
