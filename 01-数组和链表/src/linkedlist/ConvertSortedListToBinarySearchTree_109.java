/**
 * 109. 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/
 *
 * @Author: Song Ningning
 * @Date: 2020-07-01 11:59
 */
public class ConvertSortedListToBinarySearchTree_109 {

    /**
     * 和第 108. 将有序数组转换为二叉搜索树 类似
     * 不过找到链表中间结点，使用快慢指针。
     * 且 108 题中 dfs 递归函数中的 right 是包含在 nums 数组中的，这里的 tail 是不包含的，
     * 因此递归终止的条件改为：if (head == tail) return null;
     */
    public TreeNode sortedListToBST(ListNode head) {
        return dfs(head, null);
    }

    private TreeNode dfs(ListNode head, ListNode tail) {
        if (head == tail)
            return null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = dfs(head, slow);
        root.right = dfs(slow.next, tail);
        return root;
    }

}