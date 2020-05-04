import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 20. 有效的括号 【简单】
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * https://leetcode-cn.com/problems/valid-parentheses/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-28 10:35
 */
public class ValidParentheses_20 {

    public boolean isValid(String s) {


        // Deque<Character> stack = new ArrayDeque<>();
        // for (int i = 0; i < s.length(); i++) {
        //     char c = s.charAt(i);
        //     if (c == '(' || c == '[' || c == '{')
        //         stack.push(c);
        //     else {
        //         if (stack.isEmpty())
        //             return false;
        //         else if (c == ')' && stack.pop() != '(')
        //             return false;
        //         else if (c == ']' && stack.pop() != '[')
        //             return false;
        //         else if (c == '}' && stack.pop() != '{')
        //             return false;
        //     }
        // }
        // return stack.isEmpty();


        // 同样是使用栈，但是每次如果遇到左括号，就压入其对应的右括号；同时开始的时候先判断下字符串长度是否为偶数
        // 如果是右括号，如果栈为空，直接返回 false，如果不空就将栈顶元素弹出，和当前扫描到的字符比较
        // 如果不相等，返回false，因为执行的是 pop，如果是相等的，栈内元素就会减一，然后执行下一轮判断
        // 循环完整个字符串后，如果栈为空，说明全部匹配，返回 true；否则，返回 false

        // 时间复杂度：O(n)，一次只遍历给定的字符串中的一个字符并在栈上进行 O(1) 的推入或弹出操作。
        // 空间复杂度：O(n)，当我们将所有的开括号都推到栈上时以及在最糟糕的情况下，我们最终要把所有括号推到栈上。

        if (s.length() % 2 != 0)
            return false;
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '[')
                stack.push(']');
            else if (c == '{')
                stack.push('}');
            else if (stack.isEmpty() || c != stack.pop())
                return false;
        }
        return stack.isEmpty();


    }

}
