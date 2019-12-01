package com.bottomlord.week_021;

import java.util.List;

public class LeetCode_539_1_最小时间差 {
    public int findMinDifference(List<String> timePoints) {
        int[] arr = new int[timePoints.size()];
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            String[] times = timePoints.get(i).split(":");
            int hour = Integer.parseInt(times[0]), minute = Integer.parseInt(times[1]);

            arr[i] = hour * 60 + minute;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (arr[i] - arr[j] == 0) {
                    return 0;
                }

                if (arr[i] - arr[j] > 0) {
                    min = Math.min(min, arr[i] - arr[j]);
                }

                if (arr[j] - arr[i] > 0) {
                    min = Math.min(min, arr[j] - arr[i]);
                }

                min = Math.min(min, Math.min(arr[i] + 1440 - arr[j], arr[j] + 1440 - arr[i]));
            }
        }

        return min;
    }
}
