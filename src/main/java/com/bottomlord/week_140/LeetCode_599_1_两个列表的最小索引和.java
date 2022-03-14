package com.bottomlord.week_140;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-03-14 13:42:26
 */
public class LeetCode_599_1_两个列表的最小索引和 {
    public String[] findRestaurant(String[] list1, String[] list2) {
        List<String> ans = new ArrayList<>();
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.computeIfAbsent(list1[i], x -> new ArrayList<>()).add(i);
        }

        for (int i = 0; i < list2.length; i++) {
            map.computeIfAbsent(list2[i], x -> new ArrayList<>()).add(i);
        }

        int min = Integer.MAX_VALUE;
        for (Map.Entry<String, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() != 2) {
                continue;
            }

            int count = entry.getValue().get(0) + entry.getValue().get(1);

            if (count < min) {
                ans.clear();
                ans.add(entry.getKey());
                min = count;
            } else if (count == min) {
                ans.add(entry.getKey());
            }
        }

        return ans.toArray(new String[0]);
    }
}
