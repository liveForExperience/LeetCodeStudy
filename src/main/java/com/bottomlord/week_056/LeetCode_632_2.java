package com.bottomlord.week_056;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/8/2 12:33
 */
public class LeetCode_632_2 {
    public int[] smallestRange(List<List<Integer>> nums) {
        int len = nums.size();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int num : nums.get(i)) {
                List<Integer> list = map.getOrDefault(num, new ArrayList<>());
                list.add(i);
                map.put(num, list);
                min = Math.min(min, num);
                max = Math.max(max, num);
            }
        }

        int left = min, right = min - 1,
            leftRange = min, rightRange = max,
            count = 0;
        int[] bucket = new int[len];
        while (right < max) {
            right++;
            if (map.containsKey(right)) {
                for (int index : map.get(right)) {
                    bucket[index]++;
                    if (bucket[index] == 1) {
                        count++;
                    }
                }

                while (count == len) {
                    if (right - left < rightRange - leftRange) {
                        leftRange = left;
                        rightRange = right;
                    }

                    if (map.containsKey(left)) {
                        for (int index : map.get(left)) {
                            bucket[index]--;
                            if (bucket[index] == 0) {
                                count--;
                            }
                        }
                    }

                    left++;
                }
            }
        }

        return new int[]{leftRange, rightRange};
    }
}