package linkedlist;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 160. 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * 注意：
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists
 */
public class IntersectionOfTwoLinkedLists_160 {

    /**
     * 双栈
     *
     * 根据题意可知：两个链表为单链表，因此如果有公共结点，形状肯定是 Y 型，不会是 X 型，
     * 因为相交结点只有一个 next 指针。
     * 如果有公共结点，从公共结点往后只有一条链，因此可以从后往前比较。
     * 使用两个栈，首先将两个链表结点入栈，然后依次弹出栈顶结点，进行比较：
     * 如果相同，接着弹出比较，知道找到最后一个相同的结点。
     *
     * 时间复杂度：O(m + n)
     * 空间复杂度：O(m + n)
     */
    static class Solution1 {

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }
            // 不进行此项判断，下面的 while 会造成死循环
            if (headA == headB) {
                return headA;
            }
            Deque<ListNode> stackA = new LinkedList<>();
            Deque<ListNode> stackB = new LinkedList<>();
            ListNode curA = headA;
            ListNode curB = headB;
            while (curA != null) {
                stackA.push(curA);
                curA = curA.next;
            }
            while (curB != null) {
                stackB.push(curB);
                curB = curB.next;
            }
            curA = stackA.poll();
            curB = stackB.poll();
            ListNode res = null;
            while (curA == curB) {
                res = curA;
                if (!stackA.isEmpty()) {
                    curA = stackA.poll();
                }
                if (!stackB.isEmpty()) {
                    curB = stackB.poll();
                }
            }
            return res;
        }
    }


    /**
     * 双指针
     * 分别遍历两个链表，定义 countA 和 countB 记录两个链表的长度，再定义 tailA 和 tailB 记录两个链表的尾结点。
     * 如果两个尾结点不同，链表肯定不相交；
     * 如果尾结点相同，计算两个链表的长度差 n，先在较长的链表上走 n 个结点，然后同时走，两个指针肯定会在第一个交点处相交。
     *
     * 时间复杂度：O(m + n)
     * 空间复杂度：O(1)
     */
    static class Solution2 {

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }
            ListNode curA = headA;
            ListNode curB = headB;
            ListNode tailA = null;
            ListNode tailB = null;
            int countA = 0;
            int countB = 0;
            while (curA != null) {
                countA++;
                tailA = curA;
                curA = curA.next;
            }
            while (curB != null) {
                countB++;
                tailB = curB;
                curB = curB.next;
            }
            // 如果尾结点不同，肯定不相交
            if (tailA != tailB) {
                return null;
            }
            // curA 指向较长的链表，curB 指向较短的链表
            curA = countA > countB ? headA : headB;
            curB = curA == headA ? headB : headA;
            // 在长链表上先走
            int count = Math.abs(countA - countB);
            while (count != 0) {
                count--;
                curA = curA.next;
            }
            // 一起走
            while (curA != curB) {
                curA = curA.next;
                curB = curB.next;
            }
            return curA;
        }
    }

    /**
     * 方法二的另一种写法
     */
    static class Solution3 {

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }
            ListNode curA = headA;
            ListNode curB = headB;
            int count = 0;
            // 这里判断条件改为 curA.next != null
            // 因此 count 最终会比实际链表长度小一个
            // 不过由于下面的条件也是 curB.next != null
            // 因此长度差保持不变
            while (curA.next != null) {
                count++;
                curA = curA.next;
            }
            while (curB.next != null) {
                count--;
                curB = curB.next;
            }
            // 如果尾结点不同，肯定不相交
            if (curA != curB) {
                return null;
            }
            // curA 指向较长的链表，curB 指向较短的链表
            curA = count > 0 ? headA : headB;
            curB = curA == headA ? headB : headA;
            // 在长链表上先走
            count = Math.abs(count);
            while (count != 0) {
                count--;
                curA = curA.next;
            }
            // 一起走
            while (curA != curB) {
                curA = curA.next;
                curB = curB.next;
            }
            return curA;
        }
    }


    /**
     * 双指针
     *
     * 假设两个链表有交点，A 链表从头结点到相交结点长度为 a，B 链表从头结点到相交结点长度为 b，相交节点到尾结点长度为 c。
     * 两个指针 curA 和 curB，首先遍历两个链表，如果 curA 先走到了空，则令 curA 指向 B 的头结点，然后继续走，某个时刻，
     * curB 走到了空，则令 curB 指向 A 的头结点，继续走，直到 curA = curB。
     * 此时 curA 走过的路程为 a+c+b，curB 走过的路程为 b+c+a。
     *
     * 时间复杂度：O(m + n)
     * 空间复杂度：O(1)
     */
    static class Solution4 {

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) return null;
            ListNode curA = headA, curB = headB;
            while (curA != curB) {
                curA = curA == null ? headB : curA.next;
                curB = curB == null ? headA : curB.next;
            }
            return curA;
        }
    }
}