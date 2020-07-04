import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * @Author: Song Ningning
 * @Date: 2020-06-28 9:30
 */

public class LongestSubstringWithoutRepeatingCharacters_3 {

    /**
     * 滑动窗口（队列）
     *
     * 初始化一个队列，保证队列中没有重复元素，队列长度就是实时的无重复子串长度
     * 遍历字符串 s：
     * 如果队列中不包含当前字符 c，则将 c 入队，更新 res；
     * 如果队列中包含当前字符 c，将元素逐个出队，知道重复元素出队，再将 c 入队，更新 res
     *
     * 时间复杂度：O(N^2)，遍历字符串 s 的每一步需要执行队列的 contains 操作，该操作时间复杂度为 O(N)
     * 空间复杂度：O(N)
     */
    class Solution1 {

        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            if (s.length() == 1) {
                return 1;
            }
            int res = 0;
            Queue<Character> queue = new LinkedList<>();
            for (int i = 0; i < s.length(); i++) {
                if (queue.contains(s.charAt(i))) {
                    char temp = queue.poll();
                    while (temp != s.charAt(i)) {
                        temp = queue.poll();
                    }
                }
                queue.offer(s.charAt(i));
                res = Math.max(res, queue.size());
            }
            return res;
        }
    }


    /**
     * 使用两个指针 start 和 end，分别记录无重复子串的起始位置和结束位置
     *
     * 依次查看每个元素，判断在 [start...end-1] 范围是否存在当前元素：
     * 如果不存在当前元素，则检查下一个元素即可，当前无重复子串的长度为 end-start+1；
     * 如果存在当前元素，且下标为 i，则先将 start 更新为 i+1，当前无重复子串的长度为 end-start+1
     *
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     */
    class Solution2 {

        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            if (s.length() == 1) {
                return 1;
            }
            int res = 0;
            int start = 0, end = 1;
            for (end = 1; end < s.length(); end++) {
                for (int i = start; i < end; i++) {
                    if (s.charAt(i) == s.charAt(end)) {
                        start = i + 1;
                        break;
                    }
                }
                res = Math.max(res, end - start + 1);
            }
            return res;
        }
    }

    /**
     * 双指针 + 哈希表
     *
     * 但是用队列或数组的话，判断是否含有当前元素的复杂度与队列长度和数组长度有关，我们只是为了寻找到下标，因此可以用哈希表优化查找时间。
     * 哈希表的 key 记录每个字符，value 记录其在 s 中的下标。
     *
     * 用 map 来判重，key 是当前遍历到的字符，value 是当前下标；
     * 用一个 start 变量记录每个无重复子串的起始下标，初始化 start = 0
     * 遍历 s 的每个字符:
     * 如果 map 中不存在该字符，将其放入 map 中，当前无重复子串长度为 end - start + 1；
     * 如果 map 中存在该字符，且下标为 i：
     * 如果 i <= start：则直接更新当前字符对应的 value，不更改 start；
     * 如果 i > start：则首先更新 start 为 i+1，然后更新当前字符对应的 value，确保 [start...end] 没有重复元素
     *
     * 注意：用哈希表时，若 map 中存在当前元素，需要判断 i 与 start 的大小关系。
     * 因为在 map 中查找是在之前所有遍历过的元素中查找，并不是在 [start...end-1] 范围中查找，因此有可能重复元素是在 start 之前出现的，
     * 比如"abba"，遍历到最后一个 a 时，此时 start 指向之前第二个 b，如果不判断大小关系，将 start 更新为 i+1，则 start 会指向
     * 第一个 b，结果 end - start + 1 = 3.
     *
     * 时间复杂度：O(N)
     * 空间复杂度：O(N)
     */
    class Solution3 {

        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.length() == 0) {
                return 0;
            }
            if (s.length() == 1) {
                return 1;
            }
            int res = 0;
            int start = 0;
            HashMap<Character, Integer> map = new HashMap<>();
            for (int end = 0; end < s.length(); end++) {
                char c = s.charAt(end);
                if (map.containsKey(c)) {
                    start = Math.max(start, map.get(c) + 1);
                }
                map.put(c, end);
                res = Math.max(res, end - start + 1);
            }
            return res;
        }
    }
}