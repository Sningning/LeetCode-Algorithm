/**
 * 给你一个数组arr，和一个整数 target。如果可以任意选择arr中的
 * 数字，能不能累加得到target，返回true或者false
 *
 * @Author: Song Ningning
 * @Date: 2020-05-26 14:36
 */
public class IsSum {

    /*
     * f(i, sum) 表示 [0...i] 范围内，任选元素之和 sum 是否为 target
     * 因此每个位置 i 可选可不选
     */
    public static boolean sum(int[] arr, int target) {
        int sum = 0;
        return isSum(arr, 0, sum, target);
    }

    private static boolean isSum(int[] arr, int i, int sum, int target) {
        if (i == arr.length)
            return sum == target;

        if (sum == target)
            return true;

        return isSum(arr, i + 1, sum, target) || isSum(arr, i + 1, sum + arr[i], target);
    }

    public static void main(String[] args) {
        int[] arr = {1,4,8};
        int target = 12;
        System.out.println(sum(arr, target));
    }
}
