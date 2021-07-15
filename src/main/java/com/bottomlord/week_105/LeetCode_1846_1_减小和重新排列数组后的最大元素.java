package com.bottomlord.week_105;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2021/7/15 8:32
 */
public class LeetCode_1846_1_减小和重新排列数组后的最大元素 {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            queue.offer(new int[]{entry.getKey(), entry.getValue()});
        }

        int cur = 0;
        while (!queue.isEmpty()) {
            int[] element = queue.poll();
            while (element[1] > 0) {
                if (element[0] > cur) {
                    cur++;
                }
                element[1]--;
            }
        }

        return cur;
    }
}
