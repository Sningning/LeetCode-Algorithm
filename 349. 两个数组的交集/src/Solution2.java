import java.util.Arrays;
import java.util.HashSet;

/**
 * @author: Song Ningning
 * @date: 2020-03-11 18:37
 */
class Solution2 {

    // 将两个数组转换为集合 set，然后迭代较小的集合检查是否存在在较大集合中。
    // 平均情况下，这种方法的时间复杂度为 O(n+m)。
    public int[] intersection(int[] nums1, int[] nums2) {

        HashSet<Integer> set1 = new HashSet<>();
        for (int n : nums1) {
            set1.add(n);
        }

        HashSet<Integer> set2 = new HashSet<>();
        for (int n : nums2) {
            set2.add(n);
        }

        if (set1.size() < set2.size()) {
            return set_intersection(set1, set2);
        } else {
            return set_intersection(set2, set1);
        }
    }

    public int[] set_intersection(HashSet<Integer> set1, HashSet<Integer> set2) {

        int [] output = new int[set1.size()];
        int idx = 0;
        for (Integer s : set1) {
            if (set2.contains(s)) {
                output[idx++] = s;
            }
        }
        return Arrays.copyOf(output, idx);
    }
}
