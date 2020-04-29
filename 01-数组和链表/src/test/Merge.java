package test;

/**
 * 88. 合并两个有序数组【简单】
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。

 * 说明:
 *
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 *
 *
 * 示例:
 *
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 *
 * https://leetcode-cn.com/problems/merge-sorted-array/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-27 10:59
 */
public class Merge {

    public void merge(int[] nums1, int m, int[] nums2, int n) {


        // 方法 1 ：将两个数组先合并，再排序。(该方法没有充分利用两个数组已经有序的特点)
        // 时间复杂度 : O((n + m) * log(n + m))
        // 空间复杂度 : O(1)

        // for (int i = 0; i < nums2.length; i++) {
        //     nums1[m + i] = nums2[i];
        // }
        // Arrays.sort(nums1);



        // 方法 2 ：双指针 从前往后
        // 由于 nums1 是用于输出的数组，需要将 nums1 中的前 m 个元素放在辅助数组 aux 中
        // 时间复杂度：O(m + n)；空间复杂度：O(m)

        // int[] aux = new int[m];
        // for (int i = 0; i < m; i++) {
        //     aux[i] = nums1[i];
        // }
        //
        // int p1 = 0;
        // int p2 = 0;
        // for (int p = 0; p < nums1.length; p++) {
        //     if (p1 > m - 1) {
        //         nums1[p] = nums2[p2++];
        //     } else if (p2 > n - 1) {
        //         nums1[p] = aux[p1++];
        //     } else if (aux[p1] <= nums2[p2]) {
        //         nums1[p] = aux[p1++];
        //     } else {
        //         nums1[p] = nums2[p2++];
        //     }
        // }



        // 方法 3 ：双指针 / 从后往前

        // 方法二已经取得了最优的时间复杂度O(n + m)O(n+m)，但需要使用额外空间。这是由于在从头改变nums1的值时，需要把nums1中的元素存放在其他位置。
        // 可以从结尾开始改写 nums1 的值，这里没有信息，因此不需要额外空间。
        // 时间复杂度 : O(n + m)。
        // 空间复杂度 : O(1)。

        int p1 = m - 1;
        int p2 = n - 1;
        for (int p = nums1.length - 1; p >= 0; p--) {
            if (p1 < 0) {
                nums1[p] = nums2[p2--];
            } else if (p2 < 0) {
                nums1[p] = nums1[p1--];
            } else if (nums2[p2] >= nums1[p1]) {
                nums1[p] = nums2[p2--];
            } else {
                nums1[p] = nums1[p1--];
            }
        }

    }
}
