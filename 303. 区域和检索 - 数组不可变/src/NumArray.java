/**
 * @Classname: NumArray
 * @Description: TODO
 *
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 *
 * 示例：
 *
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 *
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 说明:
 *
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-query-immutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Song Ningning
 * @date: 2020-03-16 10:27
 */


/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */

class NumArray {

    // 利用线段树

    int[] data;
    int[] tree;

    public NumArray(int[] nums) {

        if (nums.length > 0) {
            data = new int[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }

            tree = new int[4 * nums.length];
            buildSegmentTree(0, 0, data.length - 1);
        }
    }

    private void buildSegmentTree(int treeIndex, int L, int R) {

        if (L == R) {
            tree[treeIndex] = data[L];
            return;
        }

        int leftChildIndex  = 2 * treeIndex + 1;
        int rightChildIndex = 2 * treeIndex + 2;

        int mid = L + (R - L) / 2;
        buildSegmentTree(leftChildIndex, L, mid);
        buildSegmentTree(rightChildIndex, mid + 1, R);

        tree[treeIndex] = tree[leftChildIndex] + tree[rightChildIndex];
    }

    // O(logn)
    public int sumRange(int i, int j) {

        if (tree == null)
            throw new IllegalArgumentException("Error!");

        if (i < 0 || i >= data.length || j < 0 ||
                j >= data.length || i > j) {
            throw new IllegalArgumentException("Error!");
        }

        return query(0, 0, data.length - 1, i, j);
    }

    private int query(int treeIndex, int L, int R, int queryL, int queryR) {

//        // 调试输出
//        System.out.println("L = " + L);
//        System.out.println("R = " + R);
//        System.out.println();

        if (L == queryL && R == queryR)
            return tree[treeIndex];

        int mid  = L + (R - L) / 2;
        int leftChildIndex  = 2 * treeIndex + 1;
        int rightChildIndex = 2 * treeIndex + 2;

        if (queryL >= mid + 1) {
            return query(rightChildIndex, mid + 1, R, queryL, queryR);
        }
        else if (queryR <= mid) {
            return query(leftChildIndex, L, mid, queryL, queryR);
        }
        else {
            int leftResult  = query(leftChildIndex, L, mid, queryL, mid);
            int rightResult = query(rightChildIndex, mid + 1, R, mid + 1, queryR);
            return leftResult + rightResult;
        }
    }

//    // 测试用
//    public int get(int index) {
//        return tree[index];
//    }

    // 测试用例
    public static void main(String[] args) {

        int[] nums = {-2, 0, 3, -5, 2, -1};

        NumArray numArray = new NumArray(nums);

//        for (int i = 0; i < 24; i++) {
//            System.out.printf("tree[%d] = %d, ",i,numArray.get(i) );
//        }
//        System.out.println();

        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }

}
