/**
 * @author: Song Ningning
 * @date: 2020-03-04 23:56
 */
class Solution2 {

    public ListNode removeElements(ListNode head, int val) {

        // 使用虚拟头结点 dummyHead
        ListNode dummyHead = new ListNode(-1); // 不关心虚拟头结点中存储的数据
        dummyHead.next = head;  // 将虚拟头结点加入链表中

        // 使用虚拟头结点后不用再考虑 第一个元素就是待删除元素 的情况
        ListNode prev = dummyHead;
        while(prev.next != null){
            if(prev.next.val == val)
                prev.next = prev.next.next;
            else
                prev = prev.next;
        }

        return dummyHead.next;
    }

    // 测试用，不要复制到 LeetCode 中
    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution2()).removeElements(head, 6);
        System.out.println(res);
    }


}
