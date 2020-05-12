/**
 * 74. 搜索二维矩阵
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *
 * https://leetcode-cn.com/problems/search-a-2d-matrix/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-12 18:07
 */
public class SearchA2dMatrix_74 {

    public boolean searchMatrix(int[][] matrix, int target) {

        // 1. 由于数组每行从左到右递增，而且从上到下递增，所以可以从左下角或者右上角开始查找。
        // 如果是右上角开始的话，当前值比 target 小，往下走；如果当前值比 target 大，往左走

        // if (matrix == null || matrix.length == 0) {
        //     return false;
        // }
        //
        // int M = 0;
        // int N = matrix[0].length - 1;
        //
        // while ( M < matrix.length && N >= 0) {
        //     if (matrix[M][N] == target) {
        //         return true;
        //     }
        //     if (matrix[M][N] < target) {
        //         M++;
        //     } else {
        //         N--;
        //     }
        // }
        // return false;


        // 2. 二分
        // 根据描述可知，该矩阵将每一行取出来放到一个数组中的话，该数组是升序数组。
        // 只要知道坐标对应关系，剩下的就是二分查找
        // 我们是将二维数组的每一行作为一组放入一维数组，
        // 因此一维数组中 index 处某一元素，在二维数组中的横坐标 i == index / 二维数组列数
        // 因此一维数组中 index 处某一元素，在二维数组中的纵坐标 j == index % 二维数组列数
        if (matrix == null || matrix.length == 0) {
            return false;
        }

        int M = matrix.length;
        int N = matrix[0].length;
        int left = 0;
        int right = M * N - 1;
        while (left <= right) {
            int mid = left + ((right - left) >>> 1);
            int i = mid / N;
            int j = mid % N;
            if (matrix[i][j] == target) {
                return true;
            }
            if (matrix[i][j] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }
}
