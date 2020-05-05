import java.util.LinkedList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 *
 * https://leetcode-cn.com/problems/combinations/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-04 21:43
 */
public class Combinations_77 {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {

        List<Integer> pre = new LinkedList<>();
        // 特判
        if (k <= 0 || n <= 0)
            return res;
        findCombinations(n, k, 1, pre);
        return res;
    }

    private void findCombinations(int n, int k, int start, List<Integer> pre) {

        if (pre.size() == k) {
            res.add(new LinkedList<>(pre));
            return;
        }

        // 将 i <= n 改为 i < = n - (k - pre.size()) + 1 是剪枝操作
        // for 循环里 i 从 start 到 n，其实没必要到 n。
        // 比如，n = 5，k = 4，pre.size( ) == 1，此时代表我们还需要（4 - 1 = 3）个数字，
        // 如果 i = 4 的话，以后最多把 4 和 5 加入到 pre 中，而此时 pre.size() 才等于 1 + 2 = 3，
        // 不够 4 个，所以 i 没必要等于 4，i 循环到 3 就足够了。
        for (int i = start; i <= n; i++) {
            pre.add(i);
            findCombinations(n, k, i + 1, pre);
            pre.remove(pre.size() - 1);
        }
    }
}
