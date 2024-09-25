package com.bottomlord.week_029;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/1/23 11:26
 */
public class LeetCode_621_1_任务调度器 {
    public int leastInterval(char[] tasks, int n) {
        int[] bucket = new int[26];
        for (char c : tasks) {
            bucket[c - 'A']++;
        }
        Arrays.sort(bucket);
        int ans = 0;

        while (bucket[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (bucket[25] == 0) {
                    break;
                }

                if (bucket[25 - i] > 0) {
                    bucket[25 - i]--;
                }

                i++;
                ans++;
            }

            Arrays.sort(bucket);
        }

        return ans;
    }
}
