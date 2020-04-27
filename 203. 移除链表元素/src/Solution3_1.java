
/**
 * @Classname: Solution3
 * @Description: TODO
 *
 * 采用递归的方式
 *
 * @author: Song Ningning
 * @date: 2020-03-05 11:19
 */
public class Solution3_1 {

    public ListNode removeElements(ListNode head, int val) {
        if(head == null)  // 递归基
            return head;

        head.next = removeElements(head.next, val);  // 除去 head，后面已经删除了 val 的链表
//        if (head.val == val)
//            return head.next;
//        else
//            return head;
        return head.val == val ? head.next : head;

    }

    // 测试用，不要复制到 LeetCode 中
    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution3_1()).removeElements(head, 6);
        System.out.println(res);
    }

}
