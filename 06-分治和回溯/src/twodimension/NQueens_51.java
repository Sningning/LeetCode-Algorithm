package twodimension;

import java.util.*;

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

    // 通过3个set来分别记录列、主对角线、副对角线上Q的情况
    Set<Integer> colSet = new HashSet<>();
    Set<Integer> masterSet = new HashSet<>();
    Set<Integer> slaveSet = new HashSet<>();

    List<List<String>> res = new LinkedList<>();

    public List<List<String>> solveNQueens(int n) {

        return null;
    }



}
