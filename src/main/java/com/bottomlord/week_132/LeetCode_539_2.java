package com.bottomlord.week_132;

import java.util.List;

/**
 * @author chen yue
 * @date 2022-01-18 09:04:50
 */
public class LeetCode_539_2 {
    public int findMinDifference(List<String> timePoints) {
        int[] bucket = new int[1441];
        for (String timePoint : timePoints) {
            int index = convert(timePoint);
            bucket[index]++;
            if (bucket[index] > 1) {
                return 0;
            }
        }

        int lastIndex = lastIndex(bucket), min = Integer.MAX_VALUE;

        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 0) {
                continue;
            }

            min = Math.min(min, (i - lastIndex + 1440) % 1440);
            lastIndex = i;
        }

        return min;
    }

    private Integer convert(String str) {
        String[] strs = str.split(":");
        return Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
    }

    private int lastIndex(int[] bucket) {
        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] != 0) {
                return i;
            }
        }

        return -1;
    }
}