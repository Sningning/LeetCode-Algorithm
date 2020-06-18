import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 90. 子集 II
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 * 输入: [1,2,2]
 * 输出:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 * https://leetcode-cn.com/problems/subsets-ii/
 *
 * @Author: Song Ningning
 * @Date: 2020-06-18 10:28
 */
public class Subsets2_90 {

    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0)
            return res;
        LinkedList<Integer> path = new LinkedList<>();
        // 排序，方便去重
        Arrays.sort(nums);
        backtrack(nums, 0, path);
        return res;
    }

    private void backtrack(int[] nums, int index, LinkedList<Integer> path) {
        res.add(new LinkedList<>(path));
        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1])
                continue;
            path.add(nums[i]);
            backtrack(nums, i + 1, path);  // 注意这里传入的应该是 i+1，不是 index+1
            path.remove(path.size() - 1);
        }
    }


    public static void main(String[] args) {
        Subsets2_90 s = new Subsets2_90();
        int[] arr = {1,2,2};
        List<List<Integer>> res = s.subsetsWithDup(arr);
        System.out.println(res);
    }
}
