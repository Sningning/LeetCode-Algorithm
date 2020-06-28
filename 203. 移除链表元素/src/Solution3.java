
/**
 * 采用递归的方式
 *
 * @author: Song Ningning
 * @date: 2020-03-05 11:19
 */
public class Solution3 {

    public ListNode removeElements(ListNode head, int val) {
        if(head == null)  // 递归基
            return head;

        ListNode res = removeElements(head.next, val);  // 除去 head，后面已经删除了 val 的链表
        if (head.val == val)
            return res;
        else {
            head.next = res;
            return head;
        }

    }

    // 测试用，不要复制到 LeetCode 中
    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution3()).removeElements(head, 6);
        System.out.println(res);
    }

}
