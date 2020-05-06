import java.util.LinkedList;
import java.util.List;

/**
 * 93. 复原IP地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 示例:
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 *
 * https://leetcode-cn.com/problems/restore-ip-addresses/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-06 11:13
 */
public class RestoreIpAddresses_93 {

    List<String> res = new LinkedList<>();

    public List<String> restoreIpAddresses(String s) {


        // 1. 暴力，4 层 for 循环
        if (s == null || s.length() < 4 || s.length() > 12) {
            return res;
        }

        StringBuilder ip = new StringBuilder();
        for (int a = 1; a < 4; a ++) {
            for (int b = 1; b < 4; b ++) {
                for (int c = 1; c < 4; c ++) {
                    for (int d = 1; d < 4; d ++) {
                        // 保证当前组合不会越界
                        // 保证当前组合与输入字符串长度相同
                        if (a + b + c + d == s.length()) {
                            int seg1 = Integer.parseInt(s.substring(0, a));
                            int seg2 = Integer.parseInt(s.substring(a, a + b));
                            int seg3 = Integer.parseInt(s.substring(a + b, a + b + c));
                            int seg4 = Integer.parseInt(s.substring(a + b + c));
                            // 保证四个段数值满足 0~255
                            if (seg1 <= 255 && seg2 <= 255 && seg3 <= 255 && seg4 <= 255 ) {
                                ip.append(seg1).append(".").append(seg2).append(".").
                                        append(seg3).append(".").append(seg4);
                                // 保障截取的字符串转成 int 后与输入字符串长度相同
                                // 字符串010010，a=1，b=1，c=1，d=3，对应字符串0，1，0，010
                                // 转成int后seg1=0，seg2=1，seg3=0，seg4=10
                                // + 3 是因为比原始字符串多出来 3 个 “.”
                                if (ip.length() == s.length() + 3) {
                                    res.add(ip.toString());
                                }
                                ip.delete(0, ip.length());
                            }
                        }
                    }
                }
            }
        }
        return res;
    }
}
