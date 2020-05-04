import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 *
 * 示例:
 *
 * 输入: [1,1,2]
 * 输出:
 * [
 *   [1,1,2],
 *   [1,2,1],
 *   [2,1,1]
 * ]
 *
 * https://leetcode-cn.com/problems/permutations-ii/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-04 18:52
 */
public class Permutations2_47 {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {

        // 特判
        if (nums.length == 0)
            return res;

        List<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        // 排序（升序或者降序都可以），排序是剪枝的前提
        Arrays.sort(nums);
        dfs(nums, path, used);
        return res;
    }

    private void dfs(int[] nums, List<Integer> path, boolean[] used) {

        if (path.size() == nums.length) {
            res.add(new LinkedList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (used[i])
                continue;

            // 剪枝条件，i > 0 保证 nums[i - 1] 有意义
            // uesd[i - 1] 是因为 nums[i - 1] 在回溯过程中被撤销了选择
            if (i > 0 && nums[i - 1] == nums[i] && used[i - 1])
                continue;

            path.add(nums[i]);
            used[i] = true;
            dfs(nums, path, used);
            used[i] = false;
            path.remove(path.size() - 1);
        }
    }
}
