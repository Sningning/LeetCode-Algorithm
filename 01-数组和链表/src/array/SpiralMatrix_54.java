package array;

import java.util.LinkedList;
import java.util.List;

/**
 * 54. 螺旋矩阵
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 * https://leetcode-cn.com/problems/spiral-matrix/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-12 14:14
 */

 /* 思路：
  *  从宏观考虑，怎样解决一圈打印，只要知道了一个矩阵的左上角坐标(a, b)和右下角坐标(c, d)，准备两个变量curR和curC，分别记录当前行和列
  *  开始时：curC ++，直到curC == d，然后此时curR++，直到curR == c，然后此时curC--，直到curC == b，最后curR--，直到curR == a。
  *  这样完成一圈的打印输出，第二圈的左上角和右上角位置分别就是，(a+1, b+1)，(c-1, d-1)。当a==c 并且b==d，时，此时打印出最后一轮结束。
  *  ------------|   |
  *  1   2   3   | 4 |
  *  ------------|   |
  * |5 |         | 8 |
  * |9 |         | 12|
  * |  |-------------
  * |13| 14  15 16
  * |  |-------------
  */


public class SpiralMatrix_54 {

    List<Integer> res=  new LinkedList<>();

    public List<Integer> spiralOrder(int[][] m) {

        if (m == null || m.length == 0) {
            return res;
        }
        int a = 0, b = 0;
        int c = m.length - 1;
        int d = m[0].length - 1;

        while (a <= c && b <=d) {
            printEdge(m, a++, b++, c--, d--);
        }

        return res;
    }

    private void printEdge(int[][] m, int a, int b, int c, int d) {

        if (a == c) {  // 只有一行
            for (int i = b; i <= d; i++) {
                res.add(m[a][i]);
            }
        } else if (b == d) {  // 只有一列
            for (int i = a; i <= c; i++) {
                res.add(m[i][b]);
            }
        } else {
            int curR = a;
            int curC = b;
            while (curC < d) {
                res.add(m[a][curC++]);
            }
            while (curR < c) {
                res.add(m[curR++][d]);
            }
            while (curC > b) {
                res.add(m[c][curC--]);
            }
            while (curR > a) {
                res.add(m[curR--][b]);
            }
        }
    }
}
