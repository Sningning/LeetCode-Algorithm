import java.util.LinkedList;
import java.util.List;

/**
 * 229. 求众数 II
 * 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 *
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
 *
 * https://leetcode-cn.com/problems/majority-element-ii/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-05 16:52
 */
public class MajorityElement2_229 {

    public List<Integer> majorityElement(int[] nums) {

        // 1. 哈希表。时间：O(N)；空间：O(N)
        // 空间复杂度没有达到 O(1)

        // Map<Integer, Integer> map = new HashMap<>();
        // LinkedList<Integer> res = new LinkedList<>();
        //
        // for (int num : nums) {
        //     if (map.containsKey(num))
        //         map.put(num, map.get(num) + 1);
        //     else
        //         map.put(num, 1);
        // }
        //
        // for (int key : map.keySet()) {
        //     if (map.get(key) > nums.length / 3)
        //         res.add(key);
        // }
        // return res;



        // 2. 摩尔投票法。时间：O(N)；空间：O(1)

        // 需要大于 [1/3]，那么最多只能有 2 个这种众数。
        // 更一般地，N 个数中，如果选出来的数的出现次数需要 > [1/k]，那么最多只能有 k-1 个这种数
        // 投票阶段：
        //     1、如果投A（当前元素等于A），A的票数++;
        //     2、如果投B（当前元素等于B），B的票数++；
        //     3、如果A,B都不投（即当前与A，B都不相等）,那么检查此时A或B的票数是否减为0，如果为0,则当前元素成为新的候选人；
        //        如果A,B两个人的票数都不为0，那么A,B两个候选人的票数均减一。
        // 最后会有这么几种可能：有2个大于N/3，有1个大于N/3，有0个大于N/3
        // 计数阶段：
        //     遍历结束后选出了两个候选人，但是这两个候选人是否满足 >N/3，还需要再遍历一遍数组，找出两个候选人的具体票数，
        //     因为题目没有像169题保证一定有。

        List<Integer> res = new LinkedList<>();
        // 特判
        if (nums == null || nums.length == 0) {
            return res;
        }
        // 初始化：定义两个候选人和各自的票数
        int candidateA = nums[0];
        int candidateB = nums[0];
        int countA = 0;
        int countB = 0;
        // 投票阶段
        for (int num : nums) {
            if (num == candidateA) {
                countA++;
            } else if (num == candidateB) {
                countB++;
            } else if (countA == 0) {
                candidateA = num;
                countA = 1;
            } else if (countB == 0) {
                candidateB = num;
                countB = 1;
            } else {
                countA --;
                countB --;
            }
        }
        // 计数阶段
        // 找到了两个候选人之后，需要确定票数是否满足大于 N/3
        countA = 0;
        countB = 0;
        for (int num : nums) {
            if (num == candidateA) {
                countA ++;
            } else if (num == candidateB) {
                countB ++;
            }
        }
        if (countA > nums.length / 3) {
            res.add(candidateA);
        }
        if (countB > nums.length / 3) {
            res.add(candidateB);
        }
        return res;

    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        MajorityElement2_229 instance = new MajorityElement2_229();
        instance.majorityElement(nums);

    }
}
