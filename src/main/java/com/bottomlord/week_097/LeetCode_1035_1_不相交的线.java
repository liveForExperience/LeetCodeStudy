package com.bottomlord.week_097;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/5/21 8:26
 */
public class LeetCode_1035_1_不相交的线 {
    private int max = 0, len1 = 0, len2 = 0;
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        len1 = nums1.length;
        len2 = nums2.length;
        Map<Integer, List<Integer>> mapping = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            List<Integer> list = mapping.getOrDefault(nums2[i], new ArrayList<>());
            list.add(i);
            mapping.put(nums2[i], list);
        }

        recuse(nums1, mapping, 0, 0, 0);
        return max;
    }

    private void recuse(int[] nums, Map<Integer, List<Integer>> mapping, int i1, int i2, int count) {
        if (i1 >= len1 || i2 >= len2) {
            max = Math.max(max, count);
            return;
        }

        for (int i = i1; i < len1; i++) {
            List<Integer> list = mapping.get(nums[i]);
            if (list == null) {
                continue;
            }

            for (int index : list) {
                if (index < i2) {
                    continue;
                }

                recuse(nums, mapping, i + 1, index + 1, count + 1);
            }
        }

        max = Math.max(max, count);
    }
}
