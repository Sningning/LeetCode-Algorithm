package linkedlist;

import java.util.HashMap;
import java.util.LinkedHashMap;
/**
 * 138. 复制带随机指针的链表
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 
 * 要求返回这个链表的 深拷贝。 
 * 
 * 我们用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * 
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 
 * 提示：
 * -10000 <= Node.val <= 10000
 * Node.random 为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 * 
 * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 * 
 * @Author: Song Ningning
 * @Date: 2020-06-13 11:00
 */

public class CopyListWithRandomPointer_138 {

    /**
     * 方法一：哈希表
     * 哈希表中的 key 存源结点，value 存源结点的拷贝；
     * 第一次遍历链表，将每个源结点和其拷贝存入哈希表中；
     * 第二次遍历链表，完成每个结点 next 和 random 指针的连接.
     * 
     * Time：O(N)
     * Space：O(N)
     */
    static class Solution1 {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }
            HashMap<Node, Node> map = new LinkedHashMap<>();
            Node cur = head;
            while (cur != null) {
                map.put(cur, new Node(cur.val));
                cur = cur.next;
            }
            cur = head;
            while (cur != null) {
                // 拷贝结点的 next = 源节点的 next 对应的 value
                map.get(cur).next = map.get(cur.next);
                // 拷贝结点的 random = 源节点的 random 对应的 value
                map.get(cur).random = map.get(cur.random);
                cur = cur.next;
            }
            return map.get(head);
        }
    }


    /**
     * 使用 O(1) 的额外空间
     * 具体操作见图示
     */
    static class Solution2 {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }
            // 克隆源节点
            Node cur = head;
            while (cur != null) {
                Node temp = new Node(cur.val);
                temp.next = cur.next;
                cur.next = temp;
                cur = cur.next.next;
            }
            // 给克隆节点的 random 赋值
            cur = head;
            while (cur != null) {
                // 注意：一定要检查 cur.random 是否为空，否则会出现空指针异常
                // 如果 cur.random 为空，那么 cur 的克隆结点的 random 也需要为空
                // 但是初始化克隆结点时，random 默认为空，因此不需要处理
                if (cur.random != null) {
                    cur.next.random = cur.random.next;
                }
                cur = cur.next.next;                
            }
            // 拆分
            cur = head;
            Node cloneCur = head.next;
            Node cloneHead = head.next;
            while (cloneCur.next != null) {
                // 修改源链表
                cur.next = cur.next.next;
                cur = cur.next;
                // 修改克隆链表
                cloneCur.next = cloneCur.next.next;
                cloneCur = cloneCur.next;
            }
            // 此时，处理完了克隆链表，但是源链表最后一个结点还指向着克隆链表最后一个结点
            // 应该将源链表最后一个结点的 next 指向 null
            cur.next = null;
            return cloneHead;
        }
    }

}