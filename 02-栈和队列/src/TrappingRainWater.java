import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 42. 接雨水【困难】
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例:
 *
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * @Author: Song Ningning
 * @Date: 2020-04-29 9:02
 */
public class TrappingRainWater {

    public int trap(int[] height) {


        // 方法一：暴力枚举。时间复杂度：O(N^2)；空间复杂度：O(1)

        // 考虑每一列。往两边遍历，分别找到两边最高的那根柱子，然后再在两边最高的中找到最矮的，因为装水高度是由矮的决定
        // 得到两个中较矮的那个后，还需要和当前那根柱子比较：
        // ① 如果 大于或者等于 当前考虑的柱子高度，肯定是无法盛水。
        // ② 如果 小于 当前考虑的柱子的高度，可以盛水，而且这一列能盛水的高度就是两者差值。

        // int res = 0;
        // if (height.length < 2)  return res;
        // //最两端的列不用考虑，因为一定不会有水。所以下标从 1 到 length - 2
        // for (int i = 1; i < height.length - 1; i++) {
        //     // 找出左边高度大于当前列高度的位置
        //     int maxL = height[i];
        //     for (int j = i - 1; j >= 0; j--) {
        //         if (height[j] > maxL) {
        //             maxL = height[j];
        //         }
        //     }
        //     // 找出右边高度大于当前列高度的位置
        //     int maxR = height[i];
        //     for (int j = i + 1; j < height.length; j++) {
        //         if (height[j] > maxR) {
        //             maxR = height[j];
        //         }
        //     }
        //     // 只有两边柱子都严格大于当前柱子高度，才会有水
        //     if (maxL > height[i] && maxR > height[i]) {
        //         res += Math.min(maxL, maxR) - height[i];
        //     }
        // }
        // return res;



        // 方法二：栈 / 单调不增栈。时间复杂度：O(N)；空间复杂度：O(N)

        // 栈内记录索引，当新入栈的元素 严格大于 栈顶元素，说明栈顶元素到了右侧边界，
        // 记录当前栈顶元素为 cur，然后弹出；弹出后，当前的栈顶元素就是左边界，
        // 找到左右边界中较小值，然后与 cur 的高度做差就是水的高度，宽度就是左右边界之间的距离

        int res = 0;
        // 特判
        if (height.length < 3)  return res;

        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = 1; i < height.length; i++) {
            // 如果栈不空 并且 当前指向的高度大于栈顶高度就一直循环
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int cur = stack.pop();  // 当前要考虑的柱子
                if (stack.isEmpty()) {  // 栈空就跳出 while，直接将新元素压入栈
                    break;
                }
                int l = stack.peek(); // cur 的左边界
                int r = i;            // cur 的右边界
                int h = Math.min(height[l], height[r]) - height[cur];  // cur 盛水的高度
                int w = r - l - 1;    // cur 盛水的宽度
                res += w * h;
            }
            stack.push(i);
        }
        return res;



        // 方法三：双指针。



    }


}
