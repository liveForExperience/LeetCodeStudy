package com.bottomlord.week_161;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2022-08-12 08:46:46
 */
public class LeetCode_1282_1_用户分组 {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            map.computeIfAbsent(groupSizes[i], x -> new ArrayList<>()).add(i);
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int len = entry.getKey();
            List<Integer> candidates = entry.getValue();
            int index = 0;

            while (index < candidates.size()) {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < len; i++) {
                    list.add(candidates.get(i));
                    index++;
                }
                ans.add(list);
            }
        }

        return ans;
    }
}
