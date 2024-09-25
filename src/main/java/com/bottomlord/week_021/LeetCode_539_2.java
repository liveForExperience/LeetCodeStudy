package com.bottomlord.week_021;

import java.util.List;

public class LeetCode_539_2 {
    public int findMinDifference(List<String> timePoints) {
        int[] arr = new int[1441];
        for (String time : timePoints) {
            String[] times = time.split(":");
            int num = Integer.parseInt(times[0]) * 60 + Integer.parseInt(times[1]);
            if (arr[num] != 0) {
                return 0;
            }

            arr[num]++;
        }

        int early = -1, first = -1, second, min = 1442;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                if (first == -1) {
                    early = i;
                    first = i;
                } else {
                    second = i;
                    min = Math.min(min, second - first);
                    first = i;
                }
            }
        }

        return Math.min(min, early + 1440 - first);
    }
}