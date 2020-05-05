import java.util.LinkedList;
import java.util.List;

/**
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * https://leetcode-cn.com/problems/subsets/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-05 14:29
 */
public class Subsets_78 {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {

        if (nums.length == 0)
            return res;
        List<Integer> path = new LinkedList<>();
        backtrack(nums, path, 0);
        return res;
    }

    private void backtrack(int[] nums, List<Integer> path, int start) {

        res.add(new LinkedList<>(path));

        //  i 从 start 开始递增
        // 同时 for 循环也隐含地包含了终止的条件，即 i == nums.length - 1
        for (int i = start; i < nums.length; i++) {
            // 做选择
            path.add(nums[i]);
            // 回溯
            backtrack(nums, path, i + 1);
            // 撤销选择
            path.remove(path.size() - 1);
        }
    }
}
