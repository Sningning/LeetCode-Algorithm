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

    private void backtrack(int[] nums, List<Integer> path, int begin) {

        // 思考角度1：按照选出几个数，类似于全排列，对于[1,2,3]，开始时当前可选 1,2,3
        // 对于 1，又可选择 2,3...，画出来的就是一个多叉树，此时要记录所有结点
        // 时间复杂度：由于总共 N 个数，树的结点个数总共为 2^N，每个结点的O(N*2^N)

        // 因为要记录所有结点，可以在最开始或者最后记录下结点信息
        // 这里选择在开始就记录结点信息（输出就是树的前序遍历结果）
        res.add(new LinkedList<>(path));

        //  i 从 start 开始递增
        // 同时 for 循环也隐含地包含了终止的条件，即 i == nums.length - 1
        for (int i = begin; i < nums.length; i++) {
            // 做选择
            path.add(nums[i]);
            // 进入下层
            backtrack(nums, path, i + 1);
            // 撤销选择
            path.remove(path.size() - 1);
        }


        // 思考角度 2：考虑当前选不选，对于[1,2,3]，即使选 1 或者不选 1，下一层又是选 2 或不选 2...
        // 因此画出来是一个二叉树，叶子节点就是需要保存的元素
        // 时间复杂度：二叉树结点总数：2^(N+1)，叶子结点 2^N，保存每个结点最多需要 N 个步骤，
        // 所以时间复杂度：O(N*2^N)
        // 空间复杂度：O(N*2^N)

        // if (index == nums.length) {
        //     res.add(new LinkedList<>(path));
        //     return;
        // }
        // // 不选择当前元素，直接进入下一层
        // backtrack(nums, path, index + 1);
        //
        // // 选择当前元素，加入 path 中
        // // 选择
        // path.add(nums[index]);
        // // 进入下层决策树
        // backtrack(nums, path, index + 1);
        // // 撤销选择
        // path.remove(path.size() - 1);

    }
}
