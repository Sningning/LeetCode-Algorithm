import java.util.HashMap;

/**
 * 260. 只出现一次的数字 III
 * 给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 *
 * 示例 :
 * 输入: [1,2,1,3,2,5]
 * 输出: [3,5]
 *
 * 注意：
 * 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
 * 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 *
 * https://leetcode-cn.com/problems/single-number-iii/
 *
 * @Author: Song Ningning
 * @Date: 2020-07-04 17:17
 */
public class SingleNumber3_260 {

    /**
     * 哈希表
     * Time: O(N); Space: O(N)
     */
    static class Solution1 {
        public int[] singleNumber(int[] nums) {
            HashMap<Integer, Boolean> map = new HashMap<>();
            for (int num : nums) {
                if (map.containsKey(num)) {
                    map.put(num, false);
                } else {
                    map.put(num, true);
                }
            }
            int[] res = new int[2];
            int i = 0;
            for (int num : map.keySet()) {
                if (map.get(num)) {
                    res[i++] = num;
                    if (i == 2) {
                        break;
                    }
                }
            }
            return res;
        }
    }


    /**
     * 如果空间复杂度为 O(1)，和上一题一样，可以先将每个数异或，由于除了有两个不同的数之外，其他均是成对出现，
     * 因此最后异或的结果 k 就是这两个数异或的结果，k = p ^ q。
     *
     * 接下来就是怎么分出 p 和 q
     * 如果把数组分成两组，p 和 q 分别在两组中，各个组中其他元素都是成对出现的，这样就和上一题一样了。
     *
     * 怎么才能按照上面说的分组呢？
     * 由于 p 和 q 不相等，k 肯定不为 0。也就是说 a 的二进制表示中至少有一位为 1。
     * 在 a 中找到从低位到高位第一个为 1 的位的位置(为了方便起见找第一个，其实都可以)，记为 n，说明 p 和 q 的二进制在 n 位上肯定不同，
     * 假设 p 的二进制在 n 位上是 1，那么 q 的二进制在 n 位上就是 0；
     * 我们按照二进制 n 位上是 1 还是 0，就可以将其他的元素分成两部分，而且保证相等的元素肯定会被分到同一组。
     *
     * 怎么才能找到这个 n 呢？
     * 在判断奇偶数时，我们通过将该数和 1 做 与 运算：
     *     如果为 0，说明该数最后一位是 0，该数是偶数；
     *     如果为 1，说明该数最后一位是 1，该数是奇数。
     * 因为我们可以将 k 先和 mask 做与运算（mask 初始值为 1）：
     *     如果不为 0，说明 k 的最低位为 1，就找到了 n；
     *     如果为 0，说明 k 的最低位为 0，将 mask 左移一位，再进行计算。
     * 这样 mask 最后的形式就是：比 n 高和比 n 低的位均为 0，只有 n 位是 1.
     * 我们就可以将数组中所有数和 mask 再做与运算，就可以将根据 num & mask == 0 或 num & mask != 0 分成两组，各自进行异或运算，找出 p、q
     *
     * 另一种找到 n 的方法：
     * 数字在计算机中是以补码形式存储，正数原码、反码、补码相同，都是正数数值本身；
     * 负数的原码是数值本身，反码的数值部分是在正数的基础上按位去翻，符号位为 1，补码的数值部分是在正数表示的基础上按位取反再加一，符号位为 1.
     * 比如：35，补码：0010 0011；-35，补码：101 1101.
     * 若 k 的二进制表示为 **** 1000，则 -k 的二进制表示为：先取反，~~~~ 0111，再加一，~~~~ 1000
     * 由于 **** 和 ~~~~ 是相反的，因此 K & (-k) 的二进制表示为：0000 1000，也就是之前求的 mask。
     */

    static class Solution2 {
        public static int[] singleNumbers(int[] nums) {
            // 所有的数异或的结果
            int k = 0;
            for (int num : nums) {
                k ^= num;
            }
            // int mask = k & (-k); 另一种找到 mask 的方法
            int mask = 1;
            while ((k & mask) == 0) {
                mask <<= 1;
            }
            int p = 0;
            int q = 0;
            for (int num : nums) {
                if ((num & mask) == 0) {
                    // n 位上等于 0 的数
                    p ^= num;
                } else {
                    // n 位上等于 1 的数
                    q ^= num;
                }
            }
            return new int[]{p, q};
        }
    }
}
