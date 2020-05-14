/**
 * @Author: Song Ningning
 * @Date: 2020-04-25 15:15
 */
public class Main {

    static int left_bound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;  // 注意
        while (left < right) {  // 注意
            int mid = left + ((right - left) >>> 1);
            if (nums[mid] == target)
                right = mid;  // 注意
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid;  // 注意
        }
        return left;
    }

    static int right_bound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >>> 1);
            if (nums[mid] == target)
                left = mid + 1;  // 注意
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid;
        }
        return left - 1;
    }

    public static void main(String[] args) {

        int[] nums = {1,2,3,4,5,6,7,8,9,9,10};
        // int[] nums = {};
        System.out.println(right_bound(nums, 1));

    }
}
