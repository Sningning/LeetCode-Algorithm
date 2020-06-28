/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Song Ningning
 * @date: 2020-03-05 17:32
 */


// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}


public class Solution {
    public ListNode reverseList(ListNode head) {
        /*
         初始化各个引用时，先不初始化 next，因为 head 可能为空.
         处理方式有两种，第一是开始就判断 head 是不是为空；第二是因为在循环时
         也要判断 head 是否为空，所以直接将 next 的初始化直接放到循环里面
         注意：最后返回时，由于此时 cur 已经为空，之前链表的最后一个节点是 pre 指向的位置，因此返回 pre
         */
        ListNode pre = null;
        ListNode cur = head;
        ListNode next;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
