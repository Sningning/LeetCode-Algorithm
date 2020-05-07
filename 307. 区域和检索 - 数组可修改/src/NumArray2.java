/**
 * @author: Song Ningning
 * @date: 2020-03-16 16:49
 */
class NumArray2 {

    // 利用线段树

    int[] data;
    int[] tree;

    public NumArray2(int[] nums) {

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
    public void update(int i, int val) {
        data[i] = val;
        update(0, 0, data.length - 1, i, val);
    }

    private void update(int treeIndex, int L, int R, int index, int e) {

        if (L == R) {
            tree[treeIndex] = e;
            return;
        }

        int mid = L + (R - L) / 2;
        int leftChildIndex  = treeIndex * 2 + 1;
        int rightChildIndex = treeIndex * 2 + 2;

        if (index <= mid) {
            update(leftChildIndex, L, mid, index, e);
        }
        else {
            update(rightChildIndex, mid + 1, R, index, e);
        }

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


    // 测试用例
    public static void main(String[] args) {

        int[] nums = {1, 3, 5};

        NumArray numArray = new NumArray(nums);

        System.out.println(numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println(numArray.sumRange(0, 2));
    }

}
