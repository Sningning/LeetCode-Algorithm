package twodimension;

/**
 * 695. 岛屿的最大面积
 * 给定一个包含了一些 0 和 1 的非空二维数组 grid 。
 *
 * 一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)
 *
 * https://leetcode-cn.com/problems/max-area-of-island/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-10 14:55
 */
public class MaxAreaOfIsland_695 {

    int rows, cols;
    int res = 0;
    int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public int maxAreaOfIsland(int[][] grid) {

        rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(grid, i, j));
                }
            }
        }
        return res;
    }

    // 每次调用的时候默认num为1，进入后判断如果不是岛屿，则直接返回0，就可以避免预防错误的情况。
    private int dfs(int[][] grid, int x, int y) {

        if (x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] == 0) {
            return 0;
        }

        // "过河拆桥"，用过了就设置为 0，这样就省了一个 visited 数组
        grid[x][y] = 0;
        int num = 1;
        for (int i = 0; i < 4; i++) {
            int newX = x + direction[i][0];
            int newY = y + direction[i][1];
            num += dfs(grid, newX, newY);
        }
        return num;
    }

    public static void main(String[] args) {
        MaxAreaOfIsland_695 instance = new MaxAreaOfIsland_695();
        int[][] grid = {{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        System.out.println(instance.maxAreaOfIsland(grid));
    }
}
