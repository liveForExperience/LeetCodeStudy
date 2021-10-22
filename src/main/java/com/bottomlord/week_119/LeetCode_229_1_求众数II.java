package com.bottomlord.week_119;

import java.util.*;

/**
 * @author chen yue
 * @date 2021-10-22 08:38:20
 */
public class LeetCode_229_1_求众数II {
    public List<Integer> majorityElement(int[] nums) {
        int maj = nums.length / 3;
        Set<Integer> ans = new HashSet<>();
        Map<Integer, Integer> mapping = new HashMap<>();
        for (int num : nums) {
            mapping.put(num, mapping.getOrDefault(num, 0) + 1);
            if (mapping.get(num) > maj) {
                ans.add(num);
            }
        }

        return new ArrayList<>(ans);
    }
}
