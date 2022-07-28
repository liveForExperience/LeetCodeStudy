package com.bottomlord.week_159;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-07-28 08:54:19
 */
public class LeetCode_1331_2 {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i);
        }

        int[] copy = Arrays.copyOf(arr, n);
        Arrays.sort(copy);

        int[] ans = new int[n];

        int rank = 1;
        for (int i = 0; i < n; i++) {
            if (i != 0 && copy[i] == copy[i - 1]) {
                continue;
            }

            int num = copy[i];
            List<Integer> indexes = map.get(num);
            for (Integer idx : indexes) {
                ans[idx] = rank;
            }

            rank++;
        }

        return ans;
    }
}
