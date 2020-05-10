package twodimension;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 *
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * https://leetcode-cn.com/problems/number-of-islands/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-10 11:26
 */
public class NumberOfIslands_200 {

    // floodfill算法，本质也是递归

    // 记录岛屿数量
    int res = 0;

    // 记录字符数组的大小
    int m, n;
    // 记录当前字符是否被考虑过
    boolean[][] visited;
    //       x-1,y
    // x,y-1  x,y   x,y+1
    //       x+1,y
    // 偏移量数组
    private int[][] direction = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int numIslands(char[][] grid) {

        m = grid.length;
        if (m == 0) {
            return 0;
        }
        n = grid[0].length;

        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    // 只有当前是 '1'，且没被访问过，才是一个新的岛屿
                    res ++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    // 从 grid[x][y] 位置开始，进行 floodfill
    // dfs 保证了 (x, y) 的合法性，且 grid[x][y]是没有访问过的陆地
    // 因此递归终止的条件是涵盖在了 if 语句中，因为不满足的话是不会再继续递归执行 dfs
    // dfs 的目的是找到和最初 (i,j) 位置相邻岛屿并全都标记，因此不需要重置 visited[x][y]
    private void dfs(char[][] grid, int x, int y) {

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int newX = x + direction[i][0];
            int newY = y + direction[i][1];
            if (inArea(newX, newY) && !visited[newX][newY] && grid[newX][newY] == '1') {
                // 未越界、未被访问过、且为 1，就在该位置继续寻找
                dfs(grid, newX, newY);
            }
        }
    }

    // 判断 x y 是否越界
    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
