package com.bottomlord.week_159;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-07-28 08:30:41
 */
public class LeetCode_1331_1_数组序号转换 {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i);
        }

        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }

        int[] x = new int[set.size()];
        int index = 0;
        for (Integer num : set) {
            x[index++] = num;
        }

        Arrays.sort(x);

        int[] ans = new int[n];

        for (int i = 0; i < x.length; i++) {
            int num = x[i];
            List<Integer> indexes = map.get(num);
            for (Integer idx : indexes) {
                ans[idx] = i + 1;
            }
        }

        return ans;
    }
}
