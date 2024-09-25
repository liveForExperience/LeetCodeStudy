package com.bottomlord.week_029;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/1/24 10:27
 */
public class LeetCode_621_2 {
    public int leastInterval(char[] tasks, int n) {
        int[] bucket = new int[26];
        for (char c : tasks) {
            bucket[c - 'A']++;
        }
        Arrays.sort(bucket);

        int max = bucket[25] - 1, idle = max * n;
        for (int i = 24; i >= 0 && bucket[i] > 0; i--) {
            idle -= Math.min(bucket[i], max);
        }

        return idle > 0 ? tasks.length + idle : tasks.length;
    }
}