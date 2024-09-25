package com.bottomlord.week_131;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2022-01-14 08:49:35
 */
public class LeetCode_373_1_查找和最小的K对数字 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> nums1[x[0]] + nums2[x[1]] - nums1[y[0]] - nums2[y[1]]);
        int len1 = nums1.length, len2 = nums2.length;

        for (int i = 0; i < Math.min(len1, k); i++) {
            queue.offer(new int[]{i, 0});
        }

        List<List<Integer>> ans = new ArrayList<>();
        int index = 0;
        while (!queue.isEmpty() && index++ < k) {
            int[] idx = queue.poll();

            List<Integer> list = new ArrayList<>();
            list.add(nums1[idx[0]]);
            list.add(nums2[idx[1]]);
            ans.add(list);

            if (idx[1] + 1 < len2) {
                queue.offer(new int[]{idx[0], idx[1] + 1});
            }
        }

        return ans;
    }
}
