package twodimension;

/**
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
 * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * https://leetcode-cn.com/problems/word-search/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-10 9:48
 */
public class WordSearch_79 {

    // 记录 board 的行数和列数
    int m, n;
    // 记录当前字符是否被考虑过
    boolean[][] visited;
    //       x-1,y
    // x,y-1  x,y   x,y+1
    //       x+1,y
    // 偏移量数组(上、右、下、左)
    private int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public boolean exist(char[][] board, String word) {

        m = board.length;
        if (m == 0) {
            return false;
        }
        n = board[0].length;

        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (searchWord(board, word, 0, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 从 board[startX][startY] 开始，寻找 word[index, word.length())
    private boolean searchWord(char[][] board, String word, int index, int startX, int startY) {

        if (index == word.length() - 1) {
            return board[startX][startY] == word.charAt(index);
        }

        // 如果当前字符匹配，接着考虑周围的字符
        if (board[startX][startY] == word.charAt(index)) {
            visited[startX][startY] = true;
            // 从 [startX][startY] 出发，向四个方向查找
            for (int i = 0; i < 4; i++) {
                int newX = startX + direction[i][0];
                int newY = startY + direction[i][1];
                if (inArea(newX, newY) && !visited[newX][newY]) {
                    if (searchWord(board, word, index + 1, newX, newY)) {
                        return true;
                    }
                }
            }
            // 如果四个方向都没找到，就放弃这个位置
            visited[startX][startY] = false;
        }
        // 如果 [startX][startY] 位置字符和 word 中 index 位置字符不匹配
        // 说明当前位置不对，直接返回
        return false;
    }

    // 判断 x y 是否越界
    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
