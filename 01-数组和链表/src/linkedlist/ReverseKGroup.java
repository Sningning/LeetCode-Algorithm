package linkedlist;

/**
 * 25. K 个一组翻转链表【困难】
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。
 *
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 示例：
 *
 * 给你这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 *
 * 说明：
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-26 15:20
 */
public class ReverseKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {

        // 思路一：
        // 用栈，把 k 个数压入栈中，然后弹出来的顺序就是翻转的！
        // 这里要注意几个问题：
        // 第一，剩下的链表个数不够 k 个不翻转；
        // 第二，已经翻转的部分要与剩下链表连接起来。

        // Stack<ListNode> stack = new Stack<>();
        // ListNode dummy = new ListNode(-1);
        // ListNode prev = dummy;
        // while (true) {
        //     int count = 0;
        //     ListNode cur = head;
        //     while (cur != null && count < k) {
        //         stack.push(cur);
        //         count ++;
        //         cur = cur.next;
        //     }
        //     // 执行完上面的 while 之后，temp 指向了下一个翻转小组的第一个结点
        //     // 如果当前翻转小组中结点个数足够 k 个，那么此时 count == k，否则 count < k
        //     if (count < k) {  // 个数不够 k 个不翻转
        //         prev.next = head;
        //         break;
        //     }
        //     // 出栈，连接结点
        //     while (!stack.empty()) {
        //         prev.next = stack.pop();
        //         prev = prev.next;
        //     }
        //     // 重置结点
        //     prev.next = cur;
        //     head = cur;
        // }
        // return dummy.next;



        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pred = dummy;  // 记录当前翻转区间的前驱
        ListNode succ = dummy;  // 记录当前翻转区间的后继
        ListNode start = dummy; // 记录当前翻转区间的第一个结点
        ListNode end = dummy;   // 记录当前翻转区间的最后一个结点

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {  // 个数不够 k 个不翻转
                break;
            }

            start = pred.next;
            succ = end.next;  // 保存后面的链表

            end.next = null;  // 先将当前部分与后面断开，为后面翻转做准备
            pred.next = reverse(start);  // 经过翻转后，end 称为该区间第一个结点，start 成为该区间最后一个结点

            start.next = succ;  // 再将当前翻转区间的最后一个结点与后面断开的链表重新连接

            pred = start;       // 重置 pred 位置
            end = pred;         // 重置 end 位置
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}