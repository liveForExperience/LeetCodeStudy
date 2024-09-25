package com.bottomlord.week_105;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class LeetCode_1331_1_数组序号转换 {
    public int[] arrayRankTransform(int[] arr) {
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> list = treeMap.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            treeMap.put(arr[i], list);
        }

        int[] ans = new int[arr.length];
        int index = 1;
        for (Integer num : treeMap.keySet()) {
            List<Integer> list = treeMap.get(num);
            for (int i : list) {
                ans[i] = index;
            }
            index++;
        }

        return ans;
    }
}
