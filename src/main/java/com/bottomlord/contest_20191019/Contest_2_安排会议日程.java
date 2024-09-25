package com.bottomlord.contest_20191019;

import java.util.*;

public class Contest_2_安排会议日程 {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        int s1 = 0, s2 = 0;
        List<Integer> list = new ArrayList<>();
        Map<Integer, int[]> map1 = new TreeMap<>();
        for (int[] arr : slots1) {
            map1.put(arr[0], arr);
        }
        int index = 0;
        for (int[] arr : map1.values()) {
            slots1[index++] = arr;
        }

        Map<Integer, int[]> map2 = new TreeMap<>();
        for (int[] arr : slots2) {
            map2.put(arr[0], arr);
        }
        index = 0;
        for (int[] arr : map2.values()) {
            slots2[index++] = arr;
        }

        while (s1 < slots1.length && s2 < slots2.length) {
            if (slots1[s1][0] > slots2[s2][1]) {
                s2++;
                continue;
            }

            if (slots2[s2][0] > slots1[s1][1]) {
                s1++;
                continue;
            }

            int start = Math.max(slots1[s1][0], slots2[s2][0]);
            int end = Math.min(slots1[s1][1], slots2[s2][1]);

            if (end - start >= duration) {
                list.add(start);
                list.add(start + duration);
                return list;
            }

            if (slots1[s1][1] > slots2[s2][1]) {
                s2++;
            } else {
                s1++;
            }
        }

        return list;
    }
}
