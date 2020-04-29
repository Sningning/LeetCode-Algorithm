package linkedlist;

/**
 * 141. 环形链表【简单】
 * 给定一个链表，判断链表中是否有环。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * https://leetcode-cn.com/problems/linked-list-cycle/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-26 13:30
 */
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {

        // 1. 哈希表。时间复杂度：O(N)；空间复杂度：O(N)。

        // 将访问过的结点存在哈希表中，每访问一个结点，先判断是否存在于哈希表中，不存在的话将新结点放入哈希表，直到遇到 null，说明没有环，
        // 如果找到了，说明有环。

        // Set<ListNode> set = new HashSet<>();
        // while (head != null) {
        //     if (set.contains(head)) {
        //         return true;
        //     }
        //     set.add(head);
        //     head = head.next;
        // }
        // return false;



        // 2. 快慢指针。时间复杂度：O(N)，N 为结点总数；空间复杂度：O(1)

        // 慢指针 slow 每次走一步，快指针 fast 每次走两步，如果有环，某个时刻肯定会有 slow == fast；如果没环，肯定出现 null
        // 当不是环形链表时:
        //     若有偶数个结点，fast 最后停在 null，即 fast == null；
        //     若有奇数个结点，fast 停在最后一个结点，即 fast.next == null

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
