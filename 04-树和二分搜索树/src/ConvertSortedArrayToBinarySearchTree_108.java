/**
 * 108. 将有序数组转换为二叉搜索树
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 * 给定有序数组: [-10,-3,0,5,9],
 *
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
 *
 * @Author: Song Ningning
 * @Date: 2020-07-01 11:59
 */
public class ConvertSortedArrayToBinarySearchTree_108 {

    /**
     * 递归 + 二分
     * 看到有序数组，首先考虑二分行不行，又因为要转换为高度平衡的 BST，因此两边尽量有相同结点即可，
     * 每次找中点作为根结点，然后左侧比它小的递归称为左子树，右侧同理。
     *
     * 类似的是 109. 有序链表转换二叉搜索树
     * https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/
     * 只不过无法直接找到链表中点，怎么找到链表的中间结点？
     * 876. 链表的中间结点
     * https://leetcode-cn.com/problems/middle-of-the-linked-list/
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] arr, int left, int right) {
        if (left > right)
            return null;
        int mid = left + ((right - left) >>> 1);
        TreeNode node = new TreeNode(arr[mid]);
        node.left = dfs(arr, left, mid - 1);
        node.right = dfs(arr, mid + 1, right);
        return node;
    }
}