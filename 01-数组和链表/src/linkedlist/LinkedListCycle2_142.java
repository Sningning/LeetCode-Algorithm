package linkedlist;

/**
 * 142. 环形链表 II【中等】
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 说明：不允许修改给定的链表。
 *
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-26 14:03
 */
public class LinkedListCycle2_142 {

    public ListNode detectCycle(ListNode head) {

        // 快慢指针。具体解释看图片

        ListNode slow = head;
        ListNode fast = head;
        while (true) {
            if (fast == null && fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

}
