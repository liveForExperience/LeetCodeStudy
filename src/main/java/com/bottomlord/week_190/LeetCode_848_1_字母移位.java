package com.bottomlord.week_190;

/**
 * @author chen yue
 * @date 2023-03-01 11:06:18
 */
public class LeetCode_848_1_字母移位 {
    public String shiftingLetters(String s, int[] shifts) {
        int n = shifts.length;
        int[] sum = new int[n];
        int pre = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum[i] = (shifts[i] + pre) % 26;
            pre = sum[i];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.toCharArray().length; i++) {
            char c = s.charAt(i);
            char nc = (char)((((c - 'a') + sum[i]) % 26) + 'a');
            sb.append(nc);
        }

        return sb.toString();
    }
}
