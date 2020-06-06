import java.util.HashMap;
import java.util.HashSet;

/**
 * 128. 最长连续序列
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 *
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 *
 * @Author: Song Ningning
 * @Date: 2020-06-06 8:58
 */
public class LongestConsecutiveSequence_128 {

    /*
     * 暴力
     *
     * 将数组元素存入哈希表中（既为后面查找做铺垫，又可以去重），遍历数组 nums
     * 不断在 set 中查看 num + 1 是否存在，存在，则 count +1
     * num 不再有 “右邻居” 了，就算出了一段连续序列的长度.
     * Time：O(N^2)
     * Space：O(N)
     *
     * 优化点：
     * 暴力破解有一个问题就是做了很多没必要的计算，因为我们要找最长的连续数字。所以如果是数组 [5 4 3 6 7]，
     * 当遇到 5 的时候计算一遍 567。遇到 4 又计算一遍 4567。遇到 3 又计算一遍 34567。很明显从 3 开始才是想要的序列。
     * 换句话讲，只考虑从序列最小的数开始即可。
     * 实现的话，当考虑 n 的时候，我们先看一看 n - 1 是否存在，如果不存在，那么从 n 开始就是我们需要考虑的序列了。否则的话，直接跳过。
     *
     * Time：O(N)
     * Space：O(N)，使用了一个和数组大小相等的哈希表
     */
    static class Solution1 {
        public static int longestConsecutive(int[] nums) {
            int res = 1;
            HashSet<Integer> set = new HashSet<>();
            for (int num : nums)
                set.add(num);

            for (int num : nums) {
                if (!set.contains(num - 1)) {
                    int i = 1;
                    int count = 1;
                    while (set.contains(num + i)) {
                        count++;
                        i++;
                    }
                    res = Math.max(res, count);
                }
            }
            return res;
        }

        public static void main(String[] args) {
            int[] nums = {100, 4, 200, 1, 3, 2};
            int count = longestConsecutive(nums);
            System.out.println(count);
        }
    }



    /*
     * 建立一个 map，key 代表当前数字，value 代表包含当前 key 的连续序列的长度。
     * key 可能是序列的最后一个，可能是序列的第一个，也可能是中间的（实际上如果是中间的数，value 已经小于所在的序列长度了，后面解释）
     *
     * [100, 4, 200, 1, 2, 3, 3]
     * 首先检查 map 中是否包含了当前元素，有的话直接考虑下一个数，如果没有的话将 {100, 1} 加入 map，表示包含 100 的连续序列长度为 1
     * 然后看 map 中是否有 99 和 101，没有，直接考虑下一个数
     * 类似依次加入 {4, 1}、{200, 1}、{1, 1}
     * 遇到 2 时，发现 map 中没有 2，首先将 {2, 1} 加入 map；寻找是否有以 3 开头的数字，没有；
     * 寻找是否有以 1 结尾的序列（为什么此时只可能是以 1 结尾的，不能是以 1 开头的，因为以 1 开头的话，后面肯定是 2，此时考虑的正是 2，由于
     *     我们考虑 map 中不包含 2 的话才会有后面逻辑，所以现在能考虑到 2，说明之前没有 2），发现有，然后修改数据 {1, 2}、{2, 2};
     * 然后考虑 3，map 中没有 3，先加入 {3, 1}，看有没有以 4 开头的，发现有 {4, 1}，此时的 1 代表以 4 开头的连续序列有一个，不表示以 4
     * 结尾 ，原因还是和刚才解释一样，如果是的话，肯定是 3 ，但现在正在考虑 3，所以 1 此时代表以 4 开头的连续序列有一个，记录下 3 后面的子序列长度：
     *  postLen = 1; 再看以 2 结尾的连续序列有没有，有，此时 {2, 2} 中的 value 表示以 2 尾结尾的连续序列为 2，为什么不是开头，也如前面所说。
     * 记录前面的长度 preLen = 2，那么，[1, 2, 3, 4]，组成了一个更长的连续序列，我们只需要修改序列两端的值就可以，即{1, 4}、{4, 4}，因为中间的 2、3 后面
     * 不会再考虑了，出现了的话也会直接在最开始去重掉，所以，连续序列中间的数字都是脏数据，不会再被考虑，我们只关心他们的 key 用于后面去重。
     *
     *
     * 左程云讲解：https://www.bilibili.com/video/BV1Pz411i7pU/?p=2&t=23
     */
    static class Solution2 {
        public static int longestConsecutive(int[] nums) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int res = 0;
            for (int num : nums) {
                if (!map.containsKey(num)) {
                    map.put(num, 1);
                    int preLen  = map.containsKey(num - 1) ? map.get(num - 1) : 0;
                    int postLen = map.containsKey(num + 1) ? map.get(num + 1) : 0;
                    int len = preLen + postLen + 1;
                    map.put(num - preLen, len);
                    map.put(num + postLen, len);
                    res = Math.max(res, len);
                }
            }
            return res;
        }

        public static void main(String[] args) {
            int[] arr1 = {100, 4, 200, 1, 3, 2};
            int count1 = longestConsecutive(arr1);
            System.out.println(count1);

            int[] arr2 = {4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3};
            int count2 = longestConsecutive(arr2);
            System.out.println(count2);  // 5
        }
    }
}
