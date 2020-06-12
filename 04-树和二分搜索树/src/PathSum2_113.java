import java.util.LinkedList;
import java.util.List;

public class PathSum2_113 {

    /**
     * 回溯
     */
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return res;
        }

        List<Integer> path = new LinkedList<>();
        pathSum(root, sum, path);
        return res;
    }

    private void pathSum(TreeNode node, int sum, List<Integer> path) {
        if (node == null)
            return;
        path.add(node.val);
        if ((node.left == null && node.right == null) && node.val == sum) {
            res.add(new LinkedList<>(path));
            path.remove(path.size() - 1);
            return;
        }
        pathSum(node.left, sum - node.val, path);
        pathSum(node.right, sum - node.val, path);
        path.remove(path.size() - 1);
    }
}