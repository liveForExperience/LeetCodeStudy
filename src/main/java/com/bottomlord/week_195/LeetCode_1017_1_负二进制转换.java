package com.bottomlord.week_195;

/**
 * @author chen yue
 * @date 2023-04-06 09:01:05
 */
public class LeetCode_1017_1_负二进制转换 {
    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        boolean even = true;
        while (n > 0 || carry != 0) {
            if (n > 0) {
                int cur = (n % 2 + carry) % 2;
                sb.insert(0, cur);
                carry = (n % 2 + carry) / 2;
                if (!even && cur == 1) {
                    carry++;
                }
                n /= 2;
            } else {
                carry--;
                sb.insert(0, 1);

                if (!even) {
                    carry++;
                }
            }

            even = !even;
        }

        return sb.toString();
    }
}
