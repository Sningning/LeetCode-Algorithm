import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 84. 柱状图中最大的矩形 【困难】
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-28 12:31
 */
public class LargestRectangleInHistogram {

    public int largestRectangleArea(int[] heights) {

        // 方法一：暴力解法。

        // 时间复杂度：O(N^2)；空间复杂度：O(1)
        // 遍历每一个柱子，对于每一个柱子，都往左和右扩散。即找到以当前柱子为高度的最大矩形面积。
        // 具体对于遍历到的当前柱子：
        //      往左侧寻找，找到 大于等于 当前柱子高度的最左侧的元素
        //      往右侧寻找，找到 大于等于 当前柱子高度的最右侧的元素

        // int len = heights.length;
        // if (len == 0)  return 0;
        //
        // int res = 0;
        // for (int i = 0; i < len; i++) {
        //     // 找左边最后 1 个大于等于 heights[i] 的索引
        //     int l = i;
        //     while (l > 0 && heights[l - 1] >= heights[i])  l --;
        //
        //     // 找右边最后 1 个大于等于 heights[i] 的索引
        //     int r = i;
        //     while (r < len - 1 && heights[r + 1] >= heights[i])  r ++;
        //
        //     // 计算面积
        //     res = Math.max(res, heights[i] * (r - l + 1));
        // }
        // return res;



        // 方法二：栈 / 单调不减栈

        // 方法一中每次都要遍历左右两部分，寻找两侧的边界，这就产生了重复工作，因为很多元素其实之前已经遍历过了。
        // 维护一个单调递增的栈。为了获取宽度，栈中记录数组下标，因为高度可以很方便的从 heights[i] 获取。
        // 核心思想还是选取中心元素尽量向两侧延申。
        // 当 heights[i] 严格小于 栈顶元素时，以栈顶元素为高的矩形已不能继续向右延伸(达到右边界)。
        // 矩形左边界就是栈内元素的前一个元素。

        int len = heights.length;
        // 特判
        if (len == 0)  return 0;
        if (len == 1)  return heights[0];

        int res = 0;
        // 新建数组，并加入头尾哨兵
        int[] newHeights = new int[len + 2];
        // 将旧数组元素拷贝到新数组，新数组[1, len]的元素是旧数组的
        // newHeights[0] 和 newHeights[len + 1] 都是零，作为哨兵
        for (int i = 0; i < len; i++) {
            newHeights[i + 1] = heights[i];
        }
        heights = newHeights;
        len += 2;

        Deque<Integer> stack = new ArrayDeque<>();
        // 先让头部哨兵入栈
        stack.push(0);

        for (int i = 1; i < len; i++) {
            while (heights[i] < heights[stack.peek()]) {
                int curHeight = heights[stack.pop()];
                int curWidth = i - stack.peek() - 1;
                res = Math.max(res, curHeight * curWidth);
            }
            stack.push(i);
        }
        return res;
    }

}
