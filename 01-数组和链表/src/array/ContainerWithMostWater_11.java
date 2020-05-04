package array;

/**
 * 11. 盛最多水的容器【中等】
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * https://leetcode-cn.com/problems/container-with-most-water/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-25 20:30
 */
public class ContainerWithMostWater_11 {

    public int maxArea(int[] height) {

        // 1. 暴力枚举。其实是求面积，可以两层循环，时间复杂度O(N^2)

        // int maxArea = 0; // 记录全局最大面积
        // for (int i = 0; i < height.length - 1; i++) {
        //     int area = 0;  // 记录当前最大面积
        //     for (int j = i + 1; j < height.length; j++) {
        //         area = Math.min(height[i], height[j]) * (j - i);
        //         maxArea = Math.max(maxArea, area);
        //     }
        // }
        // return maxArea;



        // 2. 双指针左右夹逼: 数组头尾设置指针，每次取较矮的来计算面积，然后向内移动较矮元素对应的指针。
        // 因为从左右两头开始，宽度肯定是最宽的，而且面积是由较矮的来决定。
        // 如果往里面移动高的，高的变得再高，由于矮的不变，所以面积一定小于当前的面积；
        // 如果往里面移动矮的，矮的可能变高，所以有可能出现更大的面积。
        // 时间复杂度 O(N)，双指针遍历一次底边宽度 N 。
        // 空间复杂度 O(1)，指针使用常数额外空间。

        // int i = 0;                  // 左指针
        // int j = height.length - 1;  // 右指针
        // int maxArea = 0;            // 记录全局最大面积
        // while (i < j) {
        //     if (height[i] < height[j]) {
        //         maxArea = Math.max(maxArea, height[i] * (j - i));
        //         i++;
        //     } else {
        //         maxArea = Math.max(maxArea, height[j] * (j - i));
        //         j--;
        //     }
        // }
        // return maxArea;

        int maxArea = 0;    // 记录全局最大面积
        int minHeight = 0;  // 记录较矮的
        for (int i = 0, j = height.length - 1; i < j ; ) {
            minHeight = height[i] < height[j] ? height[i ++] : height[j --];
            maxArea = Math.max(maxArea, minHeight * (j - i + 1));
        }
        return maxArea;

    }

}
