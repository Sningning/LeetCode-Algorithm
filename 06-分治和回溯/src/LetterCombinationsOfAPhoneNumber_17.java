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

    List<String> res = new LinkedList<>();

    public List<String> letterCombinations(String digits) {

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
        StringBuilder path = new StringBuilder();
        dfs(digits, path, 0, map);
        return res;
    }

    private void dfs(String digits, StringBuilder path, int start, Map<Character, String> map) {

        if (start == digits.length()) {
            res.add(new String(path));
            return;
        }

        String subStr = map.get(digits.charAt(start));

        for (int i = 0; i < subStr.length(); i++) {
            path.append(subStr.charAt(i));
            dfs(digits, path, start + 1, map);
            path.deleteCharAt(path.length() - 1);
        }

    }

    public static void main(String[] args) {

        LetterCombinationsOfAPhoneNumber_17 instance = new LetterCombinationsOfAPhoneNumber_17();
        System.out.println(instance.letterCombinations("23"));
    }
}
