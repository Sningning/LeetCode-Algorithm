import java.util.Deque;
import java.util.LinkedList;

/**
 * 739. 每日温度
 * 根据每日 气温 列表，请重新生成一个列表，对应位置的输出是需要再等待多久温度才会升高超过该日的天数。
 * 如果之后都不会升高，请在该位置用 0 来代替。
 *
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 *
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 *
 * https://leetcode-cn.com/problems/daily-temperatures/
 *
 * @Author: Song Ningning
 * @Date: 2020-06-11 09:31
 */
public class DailyTemperatures_739 {

    /**
     * 暴力
     * Time：O(N^2)
     * Space：O(1)
     */
    static class Solution {
        public int[] dailyTemperatures(int[] T) {
            int len = T.length;
            if (len == 0)
                return new int[0];
            int[] res = new int[len];
            for (int i = 0; i < len; i++) {
                for (int j = i + 1; j < len; j++) {
                    if (T[j] > T[i]) {
                        res[i] = j - i;
                        break;
                    }
                }
            }
            return res;
        }
    }


    /**
     * 单调栈（单调不增）
     * Time：O(N)
     * Space：O(N)
     */
    static class Solution2 {
        public static int[] dailyTemperatures(int[] T) {
            int len = T.length;
            if (len == 0)
                return new int[0];
            int[] res = new int[len];
            Deque<Integer> stack = new LinkedList<>();
            stack.push(0);
            for (int i = 1; i < len; i++) {
                while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                    int index = stack.pop();
                    res[index] = i - index;
                }
                stack.push(i);
            }
            return res;
        }

        public static void main(String[] args) {
            int[] arr = {73, 74, 75, 71, 69, 72, 76, 73};
            int[] res = dailyTemperatures(arr);
            for (int num : res)
                System.out.print(num + " ");
        }
    }

}