package linkedlist;

/**
 * 206. 反转链表
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * https://leetcode-cn.com/problems/reverse-linked-list/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-26 9:59
 */

public class ReverseLinkedList {

    // 配合递归的写法
    // ListNode prev = null;
    // ListNode next = null;

    public ListNode reverseList(ListNode head) {

        // 1. 双指针迭代
        ListNode prev = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;


        // 2. 利用外部空间

        // if (head == null) return null;
        // Stack<ListNode> stack = new Stack<>();
        // while (head != null) {
        //     stack.push(head);
        //     head = head.next;
        // }
        // head = stack.pop();
        // ListNode cur = head;
        // while (!stack.empty()) {
        //     cur.next = stack.pop();
        //     cur = cur.next;
        // }
        // cur.next = null;
        // return head;


        // 3.递归

        // if (head == null) {
        //     return prev;
        // }
        // next = head.next;
        // head.next = prev;
        // prev = head;
        // head = next;
        // return reverseList(head);
    }




    public static void main(String[] args) {

    }

}
