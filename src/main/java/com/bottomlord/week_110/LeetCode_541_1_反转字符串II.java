package com.bottomlord.week_110;

/**
 * @author chen yue
 * @date 2021-08-20 08:18:56
 */
public class LeetCode_541_1_反转字符串II {
    public String reverseStr(String s, int k) {
        char[] cs = s.toCharArray();
        int index = 0, n = s.length();
        while (index < n) {
            int head = index, tail = index + k - 1;

            if (tail >= n) {
                tail = n - 1;
            }

            while (head < tail) {
                char c = cs[head];
                cs[head] = cs[tail];
                cs[tail] = c;

                head++;
                tail--;
            }

            index += 2 * k;
        }

        return new String(cs);
    }
}
