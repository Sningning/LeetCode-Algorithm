/**
 * @author: Song Ningning
 * @date: 2020-03-16 14:58
 */
class NumArray2 {

    // 不涉及更新操作，可以直接用数组，初始化的时候复杂度为 O(n)，求和操作为 O(1) ，比线段树要好

    // sum[i] 存储前 i 个元素和，sum[0] = 0
    // sum[i] 实际存储 nums[0]...num[i-1] 的和
    private int[] sum;

    public NumArray2(int[] nums) {

        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }

    // 测试用例
    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};

        NumArray2 numArray = new NumArray2(nums);

        System.out.println(numArray.sumRange(0, 2));
        System.out.println(numArray.sumRange(2, 5));
        System.out.println(numArray.sumRange(0, 5));
    }

}
