import javafx.util.Pair;

import java.util.*;

/**
 * FIXME 双向
 *
 * 127. 单词接龙
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。
 * 转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 *
 * 说明:
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 *
 * https://leetcode-cn.com/problems/word-ladder/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-07 16:02
 */
public class WordLadder_127 {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // 1.单向 bfs 搜索

        // 不包含 endWord 直接返回 0
        if (!wordList.contains(endWord)) {
            return 0;
        }

        // 创建邻接图
        // map 的 key 存储通配的字符串，value 存储对应的 wordList 中字符串组成的 list
        int len = beginWord.length();
        HashMap<String, List<String>> graph = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < len; i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1);
                if (!graph.containsKey(key)) {
                    List<String> list = new LinkedList<>();
                    list.add(word);
                    graph.put(key, list);
                } else {
                    graph.get(key).add(word);
                }
            }
        }

        // 创建队列
        Queue<Pair<String, Integer>> queue = new ArrayDeque<>();
        // 记录字符串是否被访问过
        // 如果进入过队列（肯定就会被访问）就将其添加到哈希表中
        HashSet<String> visited = new HashSet<>();
        // 先将 beginWord 加入队列，同时加入哈希表
        // pair 是轻量级的一种 map，也是存储键值对
        // 这里 pair 中 key 存储当前字符串，value 记录转换序列长度
        queue.add(new Pair<>(beginWord, 1));
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            Pair<String, Integer> node = queue.poll();
            // 取出队列头结点的字符串
            String curWord = node.getKey();
            // 取出队列头结点的转换序列长度
            int level = node.getValue();
            // 针对当前字符串，循环将每个位置字符转换为“*”，构造通配字符串
            for (int i = 0; i < len; i++) {
                String key = curWord.substring(0, i) + "*" + curWord.substring(i + 1);
                // 包含的话才继续
                if (graph.containsKey(key)) {
                    // 从 graph 中取出当前通配字符串对应的字符串集合 list
                    List<String> words = graph.get(key);
                    // 遍历每个符合要求的字符串
                    for (String word : words) {
                        // 如果直接找到了 endWord，返回当前 level + 1
                        if (word.equals(endWord)) {
                            return level + 1;
                        }
                        // 如果不包含 endWord，且字符串没有被访问过
                        // 将其添加到队列和哈希表中
                        if (!visited.contains(word)) {
                            queue.add(new Pair<>(word, level + 1));
                            visited.add(word);
                        }
                    }
                }
            }
        }
        return 0;
    }


    // for test
    public static void main(String[] args) {
        WordLadder_127 instance = new WordLadder_127();
        ArrayList<String> list = new ArrayList<>();
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");
        // list.add("a");
        // list.add("b");
        // list.add("c");
        System.out.println(instance.ladderLength("hit", "cog", list));
        // System.out.println(instance.ladderLength("a", "c", list));
    }
}

class solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        // 2.双向 bfs 搜索

        // 不包含 endWord 直接返回 0
        if (!wordList.contains(endWord)) {
            return 0;
        }

        // 创建邻接图
        // map 的 key 存储通配的字符串，value 存储对应的 wordList 中字符串组成的 list
        int len = beginWord.length();
        HashMap<String, List<String>> graph = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < len; i++) {
                String key = word.substring(0, i) + "*" + word.substring(i + 1);
                if (!graph.containsKey(key)) {
                    List<String> list = new LinkedList<>();
                    list.add(word);
                    graph.put(key, list);
                } else {
                    graph.get(key).add(word);
                }
            }
        }

        // 记录字符串是否被访问过，并且记录层数
        HashMap<String, Integer> visited1 = new HashMap<>();
        HashMap<String, Integer> visited2 = new HashMap<>();
        // 创建队列
        Queue<String> queue1 = new ArrayDeque<>();
        Queue<String> queue2 = new ArrayDeque<>();
        queue1.add(beginWord);
        queue2.add(endWord);
        visited1.put(beginWord, 1);
        visited2.put(endWord, 1);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {

            // 始终从路径少的一方寻找
            if (queue1.size() <= queue2.size()) {

                String curWord = queue1.poll();
                int level = visited1.get(curWord);
                // 针对当前字符串，循环将每个位置字符转换为“*”，构造通配字符串
                for (int i = 0; i < len; i++) {
                    String key = curWord.substring(0, i) + "*" + curWord.substring(i + 1);
                    // 包含的话才继续
                    if (graph.containsKey(key)) {
                        // 从 graph 中取出当前通配字符串对应的字符串集合 list
                        List<String> words = graph.get(key);
                        // 遍历每个符合要求的字符串
                        for (String word : words) {
                            if (word.equals(endWord)) {
                                return level + 1;
                            }
                            if (visited2.containsKey(word)) {
                                return level + visited2.get(word);
                            }
                            if (!visited1.containsKey(word)) {
                                queue1.add(word);
                                visited1.put(word, level + 1);
                            }
                        }
                    }
                }
            } else {
                String curWord = queue2.poll();
                int level = visited2.get(curWord);
                // 针对当前字符串，循环将每个位置字符转换为“*”，构造通配字符串
                for (int i = 0; i < len; i++) {
                    String key = curWord.substring(0, i) + "*" + curWord.substring(i + 1);
                    // 包含的话才继续
                    if (graph.containsKey(key)) {
                        // 从 graph 中取出当前通配字符串对应的字符串集合 list
                        List<String> words = graph.get(key);
                        // 遍历每个符合要求的字符串
                        for (String word : words) {
                            if (word.equals(beginWord)) {
                                return level + 1;
                            }
                            if (visited1.containsKey(word)) {
                                return visited1.get(word) + level;
                            }
                            if (!visited2.containsKey(word)) {
                                queue2.add(word);
                                visited2.put(word, level + 1);
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
}
