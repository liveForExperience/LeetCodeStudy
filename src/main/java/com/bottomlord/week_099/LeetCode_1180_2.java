package com.bottomlord.week_099;

/**
 * @author ChenYue
 * @date 2021/6/3 9:10
 */
public class LeetCode_1180_2 {
    public int countLetters(String s) {
        int len = s.length();
        if (len == 1) {
            return 1;
        }

        int[] sums = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            sums[i] = sums[i - 1] + i;
        }

        int ans = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int count = 1, oi = i;
            while (i + 1< len && s.charAt(i +1) == c) {
                count = sums[i + 1 - (oi - 1)];
                i++;
            }
            ans += count;
        }

        return ans;
    }
}
