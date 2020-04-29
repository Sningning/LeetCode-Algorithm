/**
 * 155. 最小栈 【简单】
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 *
 * 提示：
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 *
 * https://leetcode-cn.com/problems/min-stack/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-28 11:18
 */
public class MinStack {

    // 方法一：两个栈。(data 栈和 min 栈元素个数相同的方法)

    // 保证 data 栈和 min 栈元素个数相等，min 栈栈顶保存最小元素，下面是操作：
    // push 操作：先把元素 push 到 data 栈，如果此时 min 栈为空，也 push 到 min 栈；
    //           如果 min 栈不为空，判断新元素和 min 栈栈顶元素大小：
    //              如果新元素小，min栈压入新元素；如果新元素大，再重新压入一个 min 栈栈顶元素
    // pop 操作：两个栈都 pop
    // top 操作：返回 data 栈栈顶元素
    // getMin 操作：返回 min 栈栈顶元素

    // Deque<Integer> dataStack = new ArrayDeque<>();
    // Deque<Integer> minStack = new ArrayDeque<>();
    //
    // public MinStack() {}
    //
    // public void push(int x) {
    //     dataStack.push(x);
    //     if (minStack.isEmpty())
    //         minStack.push(x);
    //     else
    //         minStack.push(x <= minStack.peek() ? x : minStack.peek());
    // }
    //
    // public void pop() {
    //     dataStack.pop();
    //     minStack.pop();
    // }
    //
    // public int top() {
    //     return dataStack.peek();
    // }
    //
    // public int getMin() {
    //     return minStack.peek();
    // }



    // 方法二：两个栈。(两个栈个数不一定要相同)

    // push 方法： 每当 push 新值进来时，如果 小于等于 min 栈顶值，则一起 push 到 min，即更新了栈顶最小值；
    // pop 方法：从 data 栈 pop，每次判断 pop 的元素是否等于 min 栈栈顶元素，如果相等，min 栈也一同pop
    // getMin 方法：返回 min 栈栈顶元素

    // Deque<Integer> dataStack = new ArrayDeque<>();
    // Deque<Integer> minStack = new ArrayDeque<>();
    //
    // public MinStack() {}
    //
    // public void push(int x) {
    //     dataStack.push(x);
    //     if (minStack.isEmpty() || x <= minStack.peek())
    //         minStack.push(x);
    // }
    //
    // public void pop() {
    //     if (dataStack.pop().equals(minStack.peek()))
    //         minStack.pop();
    // }
    //
    // public int top() {
    //     return dataStack.peek();
    // }
    //
    // public int getMin() {
    //     return minStack.peek();
    // }



    // 方法三：一个栈。

    // 只有一个 min 变量记录最小值。
    // push 方法：新元素进来的时候，先和 min 比较：
    //              如果 大于 min，直接入栈；
    //              如果 小于等于 min，为了不丢失之前的 min，先将min压入栈，再更新min，最后把新元素入栈
    // pop 方法：先判断待 pop 的元素和 min 的大小：
    //              如果 不等于 min，直接 pop 即可
    //              如果 等于 min，先 pop，然后再 pop 一次，并且把第二次 pop 的元素赋值给 min

    // Deque<Integer> stack = new ArrayDeque<>();
    // int min = Integer.MAX_VALUE;
    // public MinStack() {}
    //
    // public void push(int x) {
    //     if (x <= min) {
    //         stack.push(min);
    //         min = x;
    //     }
    //     stack.push(x);
    // }
    //
    // public void pop() {
    //     if (stack.pop() == min)
    //         min = stack.pop();
    // }
    //
    // public int top() {
    //     return stack.peek();
    // }
    //
    // public int getMin() {
    //     return min;
    // }



    // 方法四：使用链表代替栈。

    // 在链表头部添加和删除元素，和栈是类似的。设计结点，可以记录当前最小值。
    // 每个结点中的 min 记录了以该结点为头结点的链表中的最小值。

    class Node {
        int value;
        int min;
        Node next;

        Node(int x, int min) {
            this.value = x;
            this.min = min;
            this.next = null;
        }
    }

    Node head = null;
    public MinStack() {}

    public void push(int x) {
        if (head == null) {
            head = new Node(x, x);
        } else {
            Node node = new Node(x, Math.min(x, head.min));
            node.next = head;
            head = node;
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.value;
    }

    public int getMin() {
        return head.min;
    }

}
