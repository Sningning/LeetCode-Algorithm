import java.util.PriorityQueue;

/**
 * 378. 有序矩阵中第k小的元素
 * 给定一个 n x n 矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 *
 * 示例：
 * matrix = [
 *    [ 1,  5,  9],
 *    [10, 11, 13],
 *    [12, 13, 15]
 * ],
 * k = 8,
 *
 * 返回 13。
 *
 * 提示：
 * 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n^2 。
 *
 * https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/
 *
 * @Author: Song Ningning
 * @Date: 2020-07-02 15:06
 */
public class KthSmallestElementInASortedMatrix_378 {

    /**
     * 首先要注意的是，每一行递增，每一列递增，但并不是第 i 行的元素一定大于第 i+1 行的元素。
     * 比如示例中第 2 行和第 3 行都有 13。
     *
     * TopK 问题最先想到了堆结构，因为是找到第 k 小元素，可以使用一个最大堆。
     * 如果新元素比堆顶元素大，不进行操作，如果新元素小于等于堆顶元素，将堆顶元素删除，添加新元素。
     *
     * 时间复杂度：O(n^2logn)，优先队列维护堆顶时间复杂度 O(logn)，最坏情况下最后一个元素才是要找的元素，所有元素遍历一遍 n^2
     * 空间复杂度：O(n)
     */
    static class Solution1 {
        public static int kthSmallest(int[][] matrix, int k) {
            // Java 中 PriorityQueue 默认是最小堆，自己传入比较器
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> (o2 - o1));
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (maxHeap.size() < k) {
                        maxHeap.offer(matrix[i][j]);
                    } else if (matrix[i][j] <= maxHeap.peek()) {
                        maxHeap.poll();
                        maxHeap.offer(matrix[i][j]);
                    } else { // matrix[i][j] > queue.peek()
                        // 此时当前行后面的元素肯定都大于堆顶元素
                        // 直接考虑下一行
                        break;
                    }
                }
            }
            return maxHeap.peek();
        }

        public static void main(String[] args) {
            int[][] arr = {
                    {1, 10, 20},
                    {2, 11, 21},
                    {3, 12, 22}};
            System.out.println(kthSmallest(arr, 3)); // 3
        }
    }


    /**
     * 值域二分
     * 矩阵左上角和右下角分别是最小值和最大值，这样就有了一个范围。
     * 取 left = matrix[0][0], right = matrix[n-1][n-1];
     * 中间值 mid = (left + right) / 2;
     * 我们统计出矩阵中 <= mid 的数字个数 count，与 k 进行比较：
     *     如果 count < k：说明要找的数字肯定要大于 mid，在右侧，将 left 更新为 mid+1；
     *     如果 count >= k：说明要找的数字在 [left...mid] 这个范围里，将 right 更新为 mid。
     * 如果 count = k，且 mid 在矩阵中，则 mid 为要找的值。
     *
     * 视频：
     * https://www.bilibili.com/video/BV1kK411n7Bi?from=search&seid=15572351607533511481
     */
    static class Solution2 {
        public static int kthSmallest(int[][] matrix, int k) {
            int n = matrix.length;
            int left = matrix[0][0];
            int right = matrix[n - 1][n - 1];
            while (left < right) {
                int mid = left + ((right - left) >>> 1);
                int count = findNotBiggerThanMid(matrix, mid, n);
                if (count < k) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return left;
        }

        /**
         * 在排序方阵中找到不大于 mid 的元素个数
         * @param matrix 排序方阵
         * @param mid 目标值
         * @param n 方阵的行数
         * @return 不大于 mid 的元素个数
         */
        private static int findNotBiggerThanMid(int[][] matrix, int mid, int n) {
            // 从左下角按列查找，如果 matrix[i][j] <= mid，则 matrix[0...i][j] 均不大于 mid
            int i = n - 1;
            int j = 0;
            int count = 0;
            while (i >= 0 && j < n) {
                if (matrix[i][j] <= mid) {
                    // 第 j 列有 i+1 个元素 <=mid
                    count += (i + 1);
                    j++;
                } else {
                    // 第 j 列目前的数大于 mid，需要继续在当前列往上找
                    i--;
                }
            }
            return count;
        }


        public static void main(String[] args) {
            int[][] arr = {
                    {1, 10, 20},
                    {2, 11, 21},
                    {3, 12, 22}};
            System.out.println(kthSmallest(arr, 3)); // 3
        }
    }
}