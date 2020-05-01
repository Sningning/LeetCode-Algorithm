/**
 * 242. 有效的字母异位词【简单】
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 *
 * https://leetcode-cn.com/problems/valid-anagram/
 *
 * @Author: Song Ningning
 * @Date: 2020-04-30 9:26
 */
public class IsAnagram {

    public static boolean isAnagram(String s, String t) {

        // 1. 排序
        // 时间复杂度：O(NlogN)；空间复杂度：O(1)

        // if (s.length() != t.length())
        //     return false;
        // char[] s1 = s.toCharArray();
        // char[] s2 = t.toCharArray();
        // Arrays.sort(s1);
        // Arrays.sort(s2);
        // return Arrays.equals(s1, s2);


        // 2. 哈希表（记录频次）

        // if (s.length() != t.length())
        //     return false;
        // int[] alpha = new int[26];
        // for (int i = 0; i < s.length(); i++) {
        //     alpha[s.charAt(i) - 'a'] ++;
        //     alpha[t.charAt(i) - 'a'] --;
        // }
        // for (int i = 0; i < alpha.length; i++) {
        //     if (alpha[i] != 0)
        //         return false;
        // }
        // return true;

        if (s.length() != t.length())
            return false;
        int[] alpha = new int[26];
        for (char c : s.toCharArray())
            alpha[c - 'a'] ++;
        for (char c : t.toCharArray()) {
            if (alpha[c - 'a'] == 0) return false;
            alpha[c - 'a'] --;
        }
        for (int nums : alpha)
            if (nums != 0) return false;
        return true;

    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t));
    }

}
