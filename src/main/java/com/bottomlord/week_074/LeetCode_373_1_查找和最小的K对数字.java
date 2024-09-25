package com.bottomlord.week_074;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/12/7 8:58
 */
public class LeetCode_373_1_查找和最小的K对数字 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(k, Comparator.comparingInt(x -> (x[0] + x[1])));
        boolean searched = false;
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                if (sum >= max) {
                    break;
                }

                queue.offer(new int[]{nums1[i], nums2[j]});

                if (!searched && queue.size() == k) {
                    max = Integer.MIN_VALUE;
                    for (int[] nums : queue) {
                        max = Math.max(max, nums[0] + nums[1]);
                    }
                    searched = true;
                }
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        while (!queue.isEmpty() && k-- > 0) {
            int[] nums = queue.poll();
            ans.add(Arrays.asList(nums[0], nums[1]));
        }
        return ans;
    }
}
