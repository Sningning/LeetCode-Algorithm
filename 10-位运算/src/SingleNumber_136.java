import java.util.HashMap;

/**
 * 136. 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * <p>
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * <p>
 * https://leetcode-cn.com/problems/single-number/
 *
 * @Author: Song Ningning
 * @Date: 2020-07-04 17:11
 */
public class SingleNumber_136 {

    /**
     * 哈希表
     * <p>
     * 最傻白甜的方法是使用哈希表，遍历数组，标记只出现一次的元素，再遍历一遍哈希表，找到该元素返回。
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     */
    static class Solution1 {
        public static int singleNumber(int[] nums) {
            HashMap<Integer, Boolean> map = new HashMap<>();
            for (int num : nums) {
                if (map.containsKey(num)) {
                    map.put(num, false);
                } else {
                    map.put(num, true);
                }
            }
            for (int num : map.keySet()) {
                if (map.get(num)) {
                    return num;
                }
            }
            return -1;
        }
    }


    /**
     * 异或运算
     * <p>
     * 异或的性质（数学里异或的符号是 ⊕）：
     * <p>
     * 相同为零，不同为一。
     * <p>
     * 0 ⊕ 0 = 0
     * 1 ⊕ 1 = 0
     * 0 ⊕ 1 = 1
     * 1 ⊕ 0 = 1
     * <p>
     * 交换律：p ⊕ q = q ⊕ p
     * 结合律：p ⊕ (q ⊕ r) = (p ⊕ q) ⊕ r
     * 恒等率：p ⊕ 0 = p
     * 归零率：p ⊕ p = 0
     * <p>
     * 由于数组中仅有一个数字只出现一次，其他数字均出现两次，因此将数组中的数字异或一遍，
     * 出现两次的数字异或结果为 0，而 p ⊕ 0 = p，因此最后的结果就是所要找的只出现一次的数字。
     */
    static class Solution2 {
        public static int singleNumber(int[] nums) {
            int res = nums[0];
            for (int i = 1; i < nums.length; i++) {
                res ^= nums[i];
            }
            return res;
        }
    }
}
