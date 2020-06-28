/**
 * 264. 丑数 II
 * 编写一个程序，找出第 n 个丑数。
 * 丑数就是质因数只包含 2, 3, 5 的正整数。
 * 
 * 示例:
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 * 
 * 说明:  
 * 1 是丑数。
 * n 不超过1690。
 * 
 * https://leetcode-cn.com/problems/ugly-number-ii/
 * 
 * @Author: Song Ningning
 * @Date: 2020-06-28 20:20
 */
public class UglyNumber2_264 {
    
    /**
     * 如果每一个数都判断，直到丑数的个数达到 n 个，则时间复杂度是与判断的总个数有关，很多不是丑数的数也进行了多余的判断。
     * 由于丑数的因子中只包含 2、3 或 5，由此可知，除了前面几个丑数（1 2 3 5）外，后面每一个丑数，一定是之前的某个丑数
     * *2 或 *3 或 *5 得到的。
     *
     * 基于此，我们可以定义一个长度为 n 的数组 help，用来记录前 n 个丑数，再定义 3 个指针 p2、p3、p5，对 p2 指向的丑数进行 *2 操作，
     * 对 p3 指向的丑数进行 *3 操作，对 p5 指向的丑数进行 *5 操作；得到 3 个丑数，则下一个丑数一定是 3 着中较小的一个，比如该数
     * 是 2 * help[p2] 得到的，那么可知，2 * help[p2] 这个数不可能再出现在后面的丑数中了，因此右移一位 p2。如果恰巧 3 * help[p3]
     * = 2 * help[p2]，那么 3 * help[p3] 这个数同样不可能再出现在后面的丑数中，p3 右移一位，p5 同理。
     *
     * 下面代码中，之所以用并列的 if 而不用 if else-if，就是因为会有重复元素，比如 2 * help[2](3) 和 3 * help[1](2)
     */

    public int nthUglyNumber1(int n) {
        int[] help = new int[n];
        help[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        int index = 1;
        while (index < n) {
            help[index] = Math.min(2 * help[p2], Math.min(3 * help[p3], 5 * help[p5]));
            if (help[index] == 2 * help[p2]) p2++;
            if (help[index] == 3 * help[p3]) p3++;
            if (help[index] == 5 * help[p5]) p5++;
            index++;
        }
        return help[n - 1];
    }


    /**
     * 第二种写法
     * 后面的丑数肯定大于当前的丑数，因此，小于等于当前丑数的都不再考虑
     */
    public int nthUglyNumber2(int n) {
        int[] help = new int[n];
        help[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        int index = 1;
        while (index < n) {
            help[index] = Math.min(2 * help[p2], Math.min(3 * help[p3], 5 * help[p5]));
            while (help[index] >= 2 * help[p2]) p2++;
            while (help[index] >= 3 * help[p3]) p3++;
            while (help[index] >= 5 * help[p5]) p5++;
            index++;
        }
        return help[n - 1];
    }
}