package twodimension;

/**
 * 130. 被围绕的区域
 * 给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。
 *
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *
 * 解释:
 *
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。
 * 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * https://leetcode-cn.com/problems/surrounded-regions/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-10 20:02
 */
public class SurroundedRegions_130 {

    // 和 200 题类似，注意，如果 O 在边上，不可以被填充，如果在里面，但是和边上的 O 相连，也不能被填充。
    // 所以先将边界的 O 以及和边界上 O 连接的 O 全部处理掉，剩下的 O 肯定就是可以被 X 填充的

    int rows, cols;
    private int[][] direction = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public void solve(char[][] board) {

        rows = board.length;
        if (rows == 0)
            return;
        cols = board[0].length;

        for (int i = 0; i < cols; i++) {
            // 处理第一行
            if (board[0][i] == 'O')
                bfs(board, 0, i);

            // 处理最后一行
            if (board[rows - 1][i] == 'O')
                bfs(board, rows - 1, i);
        }

        for (int i = 0; i < rows; i++) {
            // 处理第一列
            if (board[i][0] == 'O')
                bfs(board, i, 0);

            // 处理最后一列
            if (board[i][cols - 1] == 'O')
                bfs(board, i, cols - 1);
        }

        // 转变
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == 'B')
                    board[i][j] = 'O';
            }
        }
    }

    private void bfs(char[][] board, int x, int y) {
        board[x][y] = 'B';
        for (int i = 0; i < 4; i++) {
            int newX = x + direction[i][0];
            int newY = y + direction[i][1];
            if (inArea(newX, newY) && board[newX][newY] == 'O')
                bfs(board, newX, newY);
        }

    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
