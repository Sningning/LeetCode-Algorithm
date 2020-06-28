/**
 * @author: Song Ningning
 * @date: 2020-03-04 23:54
 */
class Solution1_1 {
    // leetcode 中不用担心内存释放问题，所以有的可以省略
    public ListNode removeElements(ListNode head, int val) {

        while(head != null && head.val == val)
            head = head.next;

        if(head == null)
            return head;

        ListNode prev = head;
        while(prev.next != null){
            if(prev.next.val == val)
                prev.next = prev.next.next;
            else
                prev = prev.next;
        }

        return head;
    }

    // 测试用，不要复制到 LeetCode 中
    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution1_1()).removeElements(head, 6);
        System.out.println(res);
    }

}
