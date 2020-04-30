import java.util.*;

/**
 * 49. 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * https://leetcode-cn.com/problems/group-anagrams/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-30 10:00
 */
public class GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {

        // 1. 排序数组分类

        // 维护一个 map，key：排序好的字符串，value：初始输入的字符串列表
        // res = {"aet" : ["ate", "eat", "tea"],
        //        "ant" : ["nat", "tan].
        //        "abt" : ["bat"] }

        if (strs == null || strs.length == 0)  return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String key = String.valueOf(c);
            if (!map.containsKey(key))
                map.put(key, new ArrayList<>());
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());


        // 2. 无需排序。
        // 只有小写字母，先新建 26 长度的字符数组，将

        // if (strs == null || strs.length == 0)  return new ArrayList<>();
        // Map<String, List<String>> map = new HashMap<>();
        // for (String s : strs) {
        //     char[] alpha = new char[26];
        //     for (char c : s.toCharArray()) alpha[c - 'a'] ++;
        //     String key = String.valueOf(alpha);
        //     if (!map.containsKey(key)) map.put(key, new ArrayList<>());
        //     map.get(key).add(s);
        // }
        // return new ArrayList<>(map.values());

    }

    public static void main(String[] args) {
        System.out.println('c' - 'a');
    }
}
