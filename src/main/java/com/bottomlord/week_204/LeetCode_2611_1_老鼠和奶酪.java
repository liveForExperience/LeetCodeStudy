package com.bottomlord.week_204;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-06-07 09:30:32
 */
public class LeetCode_2611_1_老鼠和奶酪 {
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        int sum = 0, n = reward1.length;
        Integer[] diffs = new Integer[n];
        for (int i = 0; i < n; i++) {
            diffs[i] = reward1[i] - reward2[i];
            sum += reward2[i];
        }

        Arrays.sort(diffs, (x, y) -> y - x);

        for (int i = 0; i < k; i++) {
            sum += diffs[i];
        }

        return sum;
    }
}
