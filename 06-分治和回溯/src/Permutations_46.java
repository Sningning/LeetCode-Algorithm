import java.util.LinkedList;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * https://leetcode-cn.com/problems/permutations/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-04 15:32
 */
public class Permutations_46 {

    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {

        // 特判
        if (nums.length == 0)
            return res;

        // 一个全局的状态变量 path 记录已经选择的数字
        List<Integer> path = new LinkedList<>();
        // used 数组记录当前元素状态，是否被选择过
        boolean[] used = new boolean[nums.length];
        dfs(nums, path, used);
        return res;
    }

    private void dfs(int[] nums, List<Integer> path, boolean[] used) {

        // 终止条件
        if (path.size() == nums.length) {
            // 注意这里 add 的是对 path 的拷贝，res.add(path) 是错的
            // 因为 path 是唯一的，此时 path 是回溯到了根节点，是个空列表
            res.add(new LinkedList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i ++) {
            // 排除不合法的选择
            if (!used[i]) {
                // 做选择
                path.add(nums[i]);
                used[i] = true;
                // 进入下一层决策树
                dfs(nums, path, used);
                // 取消选择
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }

    }

}
