package linkedlist;

/**
 * 876. 链表的中间结点
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 * 示例 1：
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 *
 * 示例 2：
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 *
 * 提示：
 * 给定链表的结点数介于 1 和 100 之间。
 *
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 *
 * @Author: Song Ningning
 * @Date: 2020-07-03 12:02
 */
public class MiddleOfTheLinkedList_876 {

    /**
     * 快慢指针
     * 题目要求：如果是偶数个结点，返回靠右侧的结点。这点需要注意。
     *
     * 1 -> 2 -> 3 -> 4 -> 5
     * 应该返回 3，slow 应该指向 3，此时 fast 指向 5；
     *
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6
     * 应该返回 4，slow 应该指向 4，此时 fast 指向 null；
     *
     * 因此，循环终止的条件是 fast == null（保证偶数个时是靠右侧元素） 或者 fast.next == null（保证奇数个时是中间元素）
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    /**
     * 如果为偶数时，取靠左的中间结点，则
     * 循环终止的条件是 fast.next.next == null（保证偶数个时是靠左侧元素） 或者 fast.next == null（保证奇数个时是中间元素）
     */
    public ListNode middleNode1(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}