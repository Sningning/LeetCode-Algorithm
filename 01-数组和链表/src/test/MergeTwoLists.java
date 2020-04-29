package test;

/**
 * 21. 合并两个有序链表【简单】
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * https://leetcode-cn.com/problems/merge-two-sorted-lists/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-27 12:02
 */
public class MergeTwoLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        // // 1. 递归。
        // // 每次返回排序好的链表的头结点
        // if (l1 == null) {
        //     return l2;
        // }
        // if (l2 == null) {
        //     return l1;
        // }
        //
        // if (l1.val <= l2.val) {
        //     l1.next = mergeTwoLists(l1.next, l2);
        //     return l1;
        // } else {
        //     l2.next = mergeTwoLists(l1,l2.next);
        //     return l2;
        // }



        // 2. 迭代

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }
        // 如果其中一个为空了，直接将另一个非空链表挂在后面
        prev.next = l1 == null ? l2 : l1;
        return dummy.next;
    }




}
