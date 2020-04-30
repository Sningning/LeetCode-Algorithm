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

        if (strs.length == 0)  return new ArrayList<>();
        Map<String, List<String>> res = new HashMap<>();
        for (String s : strs) {
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String key = String.valueOf(c);
            if (!res.containsKey(key))
                res.put(key, new ArrayList<>());
            res.get(key).add(s);
        }
        return new ArrayList<>(res.values());
    }
}
