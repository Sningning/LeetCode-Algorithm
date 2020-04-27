import java.util.*;

/**
 * @Classname: Solution
 * @Description: TODO
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Song Ningning
 * @date: 2020-03-14 16:17
 */

class Solution {

    public List<Integer> topKFrequent(int[] nums, int k) {

        // 用 Map 统计每个元素的频次
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int key : nums) {
            if (map.containsKey(key))
                map.put(key, map.get(key) + 1);
            else
                map.put(key, 1);
        }

        // 创建匿名的 Comparator 比较器，比较元素 a 和元素 b 频次的大小

//        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer a, Integer b) {
//                return map.get(a) - map.get(b);
//            }
//        });

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> map.get(a) - map.get(b));

        for (int key : map.keySet()) {
            if (pq.size() < k)
                pq.add(key);
            else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }

        // 取出元素，存入 List
        LinkedList<Integer> res = new LinkedList<>();
        while (!pq.isEmpty()) {
            res.add(pq.remove());
        }

        return res;
    }

}