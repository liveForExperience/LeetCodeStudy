package com.bottomlord.week_8;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode_219_1_存在重复元素II {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            List<Integer> list;
            if (map.containsKey(num)) {
                list = map.get(num);
            } else {
                list = new ArrayList<>();
                map.put(num, list);
            }
            list.add(i);
        }

        for (List<Integer> list : map.values()) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (Math.abs(list.get(i) - list.get(j)) == k) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
