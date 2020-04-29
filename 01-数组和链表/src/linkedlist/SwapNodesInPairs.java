package linkedlist;

/**
 * 24. 两两交换链表中的节点【中等】
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-26 10:59
 */


public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {

        // 1. 递归。时间复杂度：O(N)，其中 N 指的是链表的节点数量。空间复杂度：O(N)，递归过程使用的堆栈空间。
        // 每次递归，完成 first 和 second 两个结点的交换，然后返回 second 结点给上级递归调用。
        // 递归终止条件：当前需要交换的结点为空 或 只有一个结点
        // 递归返回：调整好顺序的局部链表的头结点，即 second 结点

        // if (head == null || head.next == null) {
        //     return head;
        // }
        // ListNode first = head;
        // ListNode second = first.next;
        // first.next = swapPairs(second.next);
        // second.next = first;
        // return second;

        // 2.迭代。 用 prev 记录 first 的前驱节点。时间复杂度：O(N)，其中 N 指的是链表的节点数量。空间复杂度：O(1)。
        //
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode first = head;
        while (first != null && first.next != null) {
            ListNode second = first.next;

            // 交换
            prev.next = second;  // 第一轮执行时，dummy 和 prev 都指向了 second，从第二轮开始只有 prev 变动，dummy 始终指向第一轮的 second
            first.next = second.next;
            second.next = first;

            // 重置 prev 和 first 结点，准备下一轮迭代
            prev = first;
            first = first.next;
        }
        return dummy.next;
    }
}
