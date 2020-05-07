import java.util.ArrayList;
import java.util.TreeSet;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 *
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 * 说明:
 *
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Song Ningning
 * @date: 2020-03-11 18:12
 */
class Solution1 {
    public int[] intersection(int[] nums1, int[] nums2) {

        // 结果中每个元素必须唯一，考虑用集合（Set）
        // 时间复杂度 O(n×m)，其中 n 和 m 是数组的长度。
        TreeSet<Integer> set = new TreeSet<>();

        for (int num : nums1) {
            set.add(num);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                list.add(num);
                // 因为 num2 中可能有好几个相同的元素，为了避免多次放进 list 中，
                // 这里添加完一次后，将原来集合中的该元素删除，后面num2中即使还有当前元素，
                // 由于集合中已经被删除了，所以判断条件 set.contains(num) 为 false
                set.remove(num);
            }
        }

        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}