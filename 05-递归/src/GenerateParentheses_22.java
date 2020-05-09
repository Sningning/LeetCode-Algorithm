import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成【中等】
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * https://leetcode-cn.com/problems/generate-parentheses/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-02 9:55
 */
public class GenerateParentheses_22 {

    // 1、递归

    // 定义一个全局的 List
    ArrayList<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {

        generate(0, 0, n, "");
        return res;
    }

    private void generate(int left, int right, int n, String curStr) {

        // 左右括号都不剩余了，递归终止
        if (left == n && right == n) {
            res.add(curStr);
            return;
        }

        // 如果左括号还剩余的话，可以拼接左括号
        if (left < n)
            generate(left + 1, right, n, curStr + "(");
        // 如果右括号剩余多于左括号剩余的话，可以拼接右括号
        if (right < left)
            generate(left, right + 1, n, curStr + ")");
    }


    // 2. 回溯

    // LinkedList<String> res = new LinkedList<>();
    //
    // public List<String> generateParenthesis(int n) {
    //     // 用 StringBuilder 避免拼串
    //     StringBuilder path = new StringBuilder();
    //     generate(n, n, path);
    //     return res;
    // }
    //
    // private void generate(int left, int right, StringBuilder path) {
    //     if (left == 0 && right == 0) {
    //         res.add(new String(path));
    //         return;
    //     }
    //     if (left > 0) {
    //         path.append("(");
    //         generate(left - 1, right, path);
    //         path.deleteCharAt(path.length() - 1);
    //     }
    //     if (right > left) {
    //         path.append(")");
    //         generate(left, right - 1, path);
    //         path.deleteCharAt(path.length() - 1);
    //     }
    // }

    public static void main(String[] args) {

        GenerateParentheses_22 parentheses = new GenerateParentheses_22();
        System.out.println(parentheses.generateParenthesis(3));
    }
}
