/**
 * @Classname: NumArray
 * @Description: TODO
 *
 * 给定一个整数数组 nums，求出数组从索引 i 到 j (i ≤ j) 范围内元素的总和，包含 i, j 两点。
 *
 * update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。
 *
 * 示例:
 *
 * Given nums = [1, 3, 5]
 *
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * 说明:
 *
 * 数组仅可以在 update 函数下进行修改。
 * 你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-sum-query-mutable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author: Song Ningning
 * @date: 2020-03-16 16:12
 */

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */

// 数组方法的性能太差
class NumArray {

    private int[] sum;
    private int[] data;

    public NumArray(int[] nums) {  // O(n)

        data = new int[nums.length];
        sum = new int[nums.length + 1];
        sum[0] = 0;

        for (int i = 1; i < sum.length; i++) {
            data[i - 1] = nums[i - 1];
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public void update(int i, int val) {  // O(n)
        data[i] = val;
        for (int j = i + 1; j < sum.length; j++) {
            sum[j] = sum[j - 1] + data[j - 1];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
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


