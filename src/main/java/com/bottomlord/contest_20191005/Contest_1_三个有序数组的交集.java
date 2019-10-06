package com.bottomlord.contest_20191005;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contest_1_三个有序数组的交集 {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : arr2) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int num : arr3) {
            if (map.containsKey(num)) {
                if (map.get(num) == 2) {
                    ans.add(num);
                }
            }
        }

        return ans;
    }
}
