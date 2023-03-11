package com.bottomlord.week_191;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-03-11 14:27:18
 */
public class Interview_1705_1_字母与数字 {
    public String[] findLongestSubarray(String[] array) {
        int n = array.length;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + (Character.isDigit(array[i - 1].charAt(0)) ? 1 : -1);
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            map.computeIfAbsent(sums[i], x -> new ArrayList<>()).add(i);
        }

        int len = 0, start = -1, end = -1;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            List<Integer> list = entry.getValue();
            int left = list.get(0), right = list.get(list.size() - 1), dis = right - left;
            if (dis > len) {
                len = dis;
                start = left;
                end = right;
            } else if (dis == len && list.get(0) < start) {
                start = left;
                end = right;
            }
        }

        return len == 0 ? new String[0] : Arrays.copyOfRange(array, start, end);
    }
}
