package com.bottomlord.week_200;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-05-13 14:39:27
 */
public class LeetCode_LCP77_1_符文储备 {
    public int runeReserve(int[] runes) {
        Arrays.sort(runes);
        int max = 0, n = runes.length;
        for (int i = 0; i < n;) {
            int pre = runes[i++], cnt = 1;
            while (i < n && runes[i] - pre == 1) {
                pre = runes[i];
                cnt++;
                i++;
            }
            max = Math.max(max, cnt);
        }
        return max;
    }
}
