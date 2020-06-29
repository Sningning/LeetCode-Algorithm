package twodimension;

/**
 * 51. N皇后
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * https://leetcode-cn.com/problems/n-queens/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-05 21:07
 */
public class NQueens_51 {

    // 通过 3 个数组来分别记录列、两条对角线上 Q 的情况
    boolean[] col, dia1, dia2;

    List<List<String>> res = new LinkedList<>();

    public List<List<String>> solveNQueens(int n) {
        col = new boolean[n];
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        // row 存储第 i 行 皇后应该被放在第几列
        List<Integer> row = new LinkedList<>();

        putQueen(n, 0, row);

        return res;
    }

    // 尝试在 n 皇后问题中，摆放第 index 行皇后的位置，位置存在 row 中
    private void putQueen(int n, int index, List<Integer> row) {
        if (index == n) {
            res.add(generateBoard(n, row));
            return;
        }

        for (int i = 0; i < n; i++) {
            // 尝试将 index 行的皇后摆在第 i 列
            if (!col[i] && !dia1[index + i] && !dia2[index - i + n - 1]) {
                row.add(i);
                col[i] = true;
                dia1[index + i] = true;
                dia2[index - i + n - 1] = true;
                putQueen(n, index + 1, row);
                // 尝试在第 index 行第 i 列摆放后，撤销状态，进行下一列的尝试
                col[i] = false;
                dia1[index + i] = false;
                dia2[index - i + n - 1] = false;
                row.remove(row.size() - 1);
            }
        }
    }

    private List<String> generateBoard(int n, List<Integer> row) {
        LinkedList<String> board = new LinkedList<>();
        for (Integer num : row) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < n; i++) {
                str.append(".");
            }
            // 第 i 行，num 位置替换为 “Q”
            str.replace(num, num + 1, "Q");
            board.add(new String(str));
        }
        return board;
    }

/*
副对角 dia2 线条数：2*n - 1
每条副对角线的每个坐标[i, j]之和是固定的，从 0 开始，一直到 2*n - 2，所以dia1[2*n - 1]，记录每条副对角线的状态；

主对角 dia1 线条数：2*n - 1
每条主对角线的每个坐标[i, j]之差(i - j)是固定的，如果是 4 皇后的话，依次为 -3,-2,-1,0,1,2,3，
为了使用状态数组记录，偏移 3 个单位，所以 dia2[2*n - 1]，记录每条主对角线的状态

 */

    public static void main(String[] args) {
        int n = 4;
        NQueens_51 queen = new NQueens_51();
        List<List<String>> lists = queen.solveNQueens(n);
        for (List<String> list : lists) {
            for (int i = 0; i < n; i++) {
                System.out.println(list.get(i));
            }
            System.out.println();
        }
    }
}

class Solution {

    /*
    用数组存储两个对角线的信息时，需要换算，计算数组下标，这里选择使用哈希表来记录
    由于 row 变量都是在末尾添加或者回溯删除，所以这里换成栈来实现
     */

    Set<Integer> col = new HashSet<>();
    Set<Integer> dia1 = new HashSet<>();
    Set<Integer> dia2 = new HashSet<>();
    List<List<String>> res = new LinkedList<>();

    public List<List<String>> solveNQueens(int n) {

        Deque<Integer> stack = new LinkedList<>();
        putQueen(n, 0, stack);
        return res;
    }

    private void putQueen(int n, int index, Deque<Integer> stack) {
        if (index == n) {
            res.add(generateBoard(n, stack));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!col.contains(i) && !dia1.contains(index + i) && !dia2.contains(index - i)) {
                stack.push(i);
                col.add(i);
                dia1.add(index + i);
                dia2.add(index - i);
                putQueen(n, index + 1, stack);
                col.remove(i);
                dia1.remove(index + i);
                dia2.remove(index - i);
                stack.pop();
            }
        }
    }

    private List<String> generateBoard(int n, Deque<Integer> stack) {
        LinkedList<String> board = new LinkedList<>();
        for (Integer num : stack) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < n; i++) {
                str.append(".");
            }
            // 第 i 行，num 位置替换为 “Q”
            str.replace(num, num + 1, "Q");
            board.add(str.toString());
        }
        return board;
    }

    public static void main(String[] args) {
        int n = 4;
        Solution queen = new Solution();
        List<List<String>> lists = queen.solveNQueens(n);
        for (List<String> list : lists) {
            for (int i = 0; i < n; i++) {
                System.out.println(list.get(i));
            }
            System.out.println();
        }
    }

}

class Solution2 {
    // 位运算
}
