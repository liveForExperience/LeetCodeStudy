package com.bottomlord.week_106;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_1743_1_从相邻元素对还原数组 {
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] pair : adjacentPairs) {
            map.putIfAbsent(pair[0], new ArrayList<>());
            map.putIfAbsent(pair[1], new ArrayList<>());
            map.get(pair[0]).add(pair[1]);
            map.get(pair[1]).add(pair[0]);
        }

        int[] ans = new int[adjacentPairs.length + 1];
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int key = entry.getKey();
            List<Integer> list = entry.getValue();

            if (list.size() == 1) {
                ans[0] = key;
                break;
            }
        }

        ans[1] = map.get(ans[0]).get(0);

        for (int i = 2; i < ans.length; i++) {
            List<Integer> list = map.get(ans[i - 1]);
            int num = list.get(0) == ans[i - 2] ? list.get(1) : list.get(0);
            ans[i] = num;
        }

        return ans;
    }
}
