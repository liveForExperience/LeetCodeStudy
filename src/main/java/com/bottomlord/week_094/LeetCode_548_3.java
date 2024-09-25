package com.bottomlord.week_094;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2021/4/27 15:53
 */
public class LeetCode_548_3 {
    public boolean splitArray(int[] nums) {
        int len = nums.length;
        int[] sums = new int[len];
        sums[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i < len; i++) {
            int one = sums[i - 1];
            for (int j = i + 1; j < len; j++) {
                if (sums[j - 1] - sums[i] == sums[i - 1]) {
                    List<Integer> list = map.getOrDefault(one, new ArrayList<>());
                    list.add(j);
                    map.put(one, list);
                }
            }
        }

        for (Integer sum : map.keySet()) {
            List<Integer> list = map.get(sum);

            for (Integer start : list) {
                for (int i = start + 1; i < len; i++) {
                    int three = sums[i - 1] - sums[start];
                    if (three != sum) {
                        continue;
                    }

                    for (int j = i + 1; j < len; j++) {
                        if (three == sums[len - 1] - sums[i]) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
