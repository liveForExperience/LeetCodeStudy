package com.bottomlord.week_070;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/11/9 8:34
 */
public class LeetCode_325_2 {
    public int maxSubArrayLen(int[] nums, int k) {
        int len = nums.length, sum = 0;
        int[] sums = new int[len];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            sums[i] = (sum += nums[i]);

            List<Integer> list = map.getOrDefault(sum, new ArrayList<>());
            list.add(i);
            map.put(sum, list);
        }

        int max = 0;
        for (int i = 0; i < len; i++) {
            int num = sums[i] - k + nums[i];
            if (map.containsKey(num)) {
                for (int index : map.get(num)) {
                    max = Math.max(max, i - index + 1);
                }
            }
        }

        return max;
    }
}
