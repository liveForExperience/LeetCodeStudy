package com.bottomlord.week_073;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2020/12/4 9:01
 */
public class LeetCode_659_1_分割数组为连续子序列 {
    public boolean isPossible(int[] nums) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int num : nums) {
            if (!map.containsKey(num)) {
                map.put(num, new PriorityQueue<>());
            }

            if (map.containsKey(num - 1)) {
                Integer preLen = map.get(num - 1).poll();
                map.get(num).offer(preLen + 1);

                if (map.get(num - 1).isEmpty()) {
                    map.remove(num - 1);
                }
            } else {
                map.get(num).offer(1);
            }
        }

        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            PriorityQueue<Integer> queue = entry.getValue();
            for (Integer num : queue) {
                if (num < 3) {
                    return false;
                }
            }
        }

        return true;
    }
}
