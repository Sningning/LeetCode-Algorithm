import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 注意 1 不对应任何字母。
 *
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-05 18:52
 */
public class LetterCombinationsOfAPhoneNumber_17 {

    // 声明为类的成员变量
    List<String> res = new LinkedList<>();

    public List<String> letterCombinations(String digits) {

        // 注意考虑：
        // 1. 字符串的合法性，是否包含 1 等没有字母的键？本题题干说明不包含。
        // 2. 输入空字符串应该怎样输出？本题输出空字符串。
        // 3. 多个解的顺序性。本题题干说明不要求有序。

        if (digits == null || digits.length() == 0) {
            return res;
        }
        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        // 为了避免拼接字符，这里使用了 StringBuilder，每次传入的就是 path
        // 所以在循环最后要删除当前循环添加的字符
        StringBuilder path = new StringBuilder();
        findCombination(digits, 0, path, map);
        return res;
    }

    private void findCombination(String digits, int index, StringBuilder path, Map<Character, String> map) {

        if (index == digits.length()) {
            // index 到达 digits 的末尾，此时获得的 path 已经是一个解了
            res.add(new String(path));
            return;
        }
        // c 记录 digits 字符串中 index 位置的数字字符
        char c = digits.charAt(index);
        // letters 记录 index 位置数字字符代表的字母
        String letters = map.get(c);

        for (int i = 0; i < letters.length(); i++) {
            path.append(letters.charAt(i));
            findCombination(digits, index + 1, path, map);
            path.deleteCharAt(path.length() - 1);
        }

    }

    public static void main(String[] args) {

        LetterCombinationsOfAPhoneNumber_17 instance = new LetterCombinationsOfAPhoneNumber_17();
        System.out.println(instance.letterCombinations("234"));
    }
}
