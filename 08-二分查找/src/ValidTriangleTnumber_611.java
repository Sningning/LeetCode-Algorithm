import java.util.Arrays;

/**
 * 611. 有效三角形的个数
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 * 
 * 示例 1:
 * 输入: [2,2,3,4]
 * 输出: 3
 * 解释:
 * 有效的组合是: 
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 * 
 *  注意:
 * 数组长度不超过1000。
 * 数组里整数的范围为 [0, 1000]。
 * 
 */
public class ValidTriangleTnumber_611 {


    /*
     * 三条边构成三角形的充要条件：① 三角形的任意两边之和大于第三边，② 三角形的任意两边之差小于第三边。
     * 只要较短的两条边大于最长的那条边，就可以满足第一个条件；
     * 假设 a < b < c, 如果 a + b > c，即 短 + 中 > 长，那么 长 - 短 < 中 和 长 - 中 < 短 也一定成立。
     * 因此，三条边能够成三角形的充分必要条件是：较短的两边之和大于（不包括等于）第三边（最长边）。
     * 
     * 因为涉及到 短中长 顺序性，可以考虑现将数组排序，然后每次先固定最短的两条边 a，b，在后面的元素中找到第一个不满足 a + b > c
     * 的边，由于是排序数组，后面也肯定不满足，因此[b+1, c-1]的元素个数都是满足的。
     *  
     * 设置变量 i 和 j，先固定 i 和 j，然后在 j 之后的范围寻找不满足要求的元素下标，由于是有序的，因此考虑使用二分查找。
     * 
     * Time：O(N^2logN)
     * Space：O(logN)，为排序需要的空间。
     */
    static class Solution1 {

        public static int triangleNumber(int[] nums) {
            Arrays.sort(nums);
            int res = 0;
            int len = nums.length;
            for (int i = 0; i < len - 2; i++) {
                for (int j = i + 1; j < len - 1; j++) {
                    int target = nums[i] + nums[j];
                    int k = findFirstCanNotTriangle(nums, j + 1, len - 1, target);
                    // 如果 k == -1，表示所考察的范围 [j+1, len-1] 的数均满足构成三角形的条件，即 均严格小于 target
                    // 总共个数为 len-1 - (j+1) + 1 = len - j - 1
                    if (k == -1) {
                        res += len - j - 1;
                    } else { 
                        // 如果返回的 k != -1，说明有两种情况：
                        //   ① [j+1, len-1] 所有元素均大于或等于 target，此时返回的是 j + 1，即 [j+1, len-1] 均不能构成三角形
                        //   ② 如果返回的 k != j+1，说明 [j+1, k-1] 区间的元素是可以构成三角形的，个数为 k-1 - (j+1) + 1 = k - j - 1
                        // 可以看到如果 k == j+1，k-j-1 == 0，因此这两种情况所能构成的三角形个数为 k-j-1
                        res += k - j - 1;
                    }
                }
                
            }
            return res;
        }

        /**
         * 在排序数组 nums 中查找第一个大于等于 target 的元素下标
         * @param nums 排序数组
         * @param left 左指针
         * @param right 右指针
         * @param target 目标元素
         * @return 第一个大于等于 target 的元素位置，不存在返回 -1
         */
        private static int findFirstCanNotTriangle(int[] nums, int left, int right, int target) {
            while (left < right) {
                int mid = left + ((right - left) >>> 1);
                if (nums[mid] < target)
                    left = mid + 1;
                else
                    right = mid;
            }
            return nums[left] < target ? -1 : left;
        }


        public static void main(String[] args) {
            int[] nums = {2,2,3,4};
            int count = triangleNumber(nums);
            System.out.println(count);  // 3
        }
    }


    /**
     * 双指针。
     * 首先对数组排序。
     * 固定最长的一条边（最右侧）索引 i，指针 left 初始在 0 位置，指针 right 初始在 i-1 位置：
     * 如果 nums[left] + nums[right] > nums[i]，同时说明 nums[left + 1] + nums[right] > nums[i], ..., 
     *     nums[right - 1] + nums[right] > nums[i]，满足的条件的有 rigtht - left 个，然后尝试缩小 nums[right]，即 right 左移进入下一轮。
     * 如果 nums[left] + nums[right] <= nums[i]，说明应该增大两数的和，因此尝试增大 nums[left]，即 left 右移进入下一轮。
     * 
     * Time：O(N^2)
     * Space：O(logN)，为排序需要的空间。
     */
    static class Solution2 {

        public static int triangleNumber(int[] nums) {
            Arrays.sort(nums);
            int res = 0;
            int len = nums.length;
            int left, right;
            for (int i = len - 1; i >= 2; i--) {
                left = 0;
                right = i - 1;
                while (left < right) {
                    if (nums[left] + nums[right] > nums[i]) {
                        res += right - left;
                        right--;
                    } else {
                        left++;
                    } 
                }
            }
            return res;
        }


        public static void main(String[] args) {
            int[] nums = {2,2,3,4};
            int count = triangleNumber(nums);
            System.out.println(count);  // 3
        }
    }

}