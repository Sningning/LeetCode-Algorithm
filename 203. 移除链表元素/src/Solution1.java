/**
 * @Classname: Solution
 * @Description: TODO
 *
 * 删除链表中等于给定值 val 的所有节点。
 *
 * 示例:
 *
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-linked-list-elements/
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Song Ningning
 * @date: 2020-03-04 23:42
 */
class Solution1 {

    public ListNode removeElements(ListNode head, int val) {

        // 第一个元素即为待删除元素的情况
        while(head != null && head.val == val){
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        // 链表为空的情况
        if(head == null)
            return head;

        // 链表不为空，且第一个元素不是待删除元素
        ListNode prev = head;
        while(prev.next != null){
            if(prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                // if 这个循环中不需要执行 prev = prev.next; 因为进入 if 语句的话，删除元素后自动跳转到下一个节点
            }
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

        ListNode res = (new Solution1()).removeElements(head, 6);
        System.out.println(res);
    }
}
