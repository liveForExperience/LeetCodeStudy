package com.bottomlord.week_056;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2020/8/1 11:56
 */
public class LeetCode_632_1_最小区间 {
    public int[] smallestRange(List<List<Integer>> nums) {
        int left = 0, right = Integer.MAX_VALUE;
        int max = 0, minRange = right - left;
        int size = nums.size();
        int[] next = new int[size];

        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return nums.get(o1).get(next[o1]) - nums.get(o2).get(next[o2]);
            }
        });

        for (int i = 0; i < size; i++) {
            queue.offer(i);
            max = Math.max(max, nums.get(i).get(next[i]));
        }

        while (true) {
            Integer minIndex = queue.poll();
            if (minIndex == null) {
                continue;
            }

            int curRange = max - nums.get(minIndex).get(next[minIndex]);
            if (curRange < minRange) {
                minRange = curRange;
                left = nums.get(minIndex).get(next[minIndex]);
                right = max;
            }

            next[minIndex]++;
            if (next[minIndex] == nums.get(minIndex).size()) {
                break;
            }

            queue.offer(minIndex);
            max = Math.max(max, nums.get(minIndex).get(next[minIndex]));
        }

        return new int[]{left, right};
    }
}
