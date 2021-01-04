package com.bottomlord.week_078;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2021/1/4 8:35
 */
public class LeetCode_239_1_滑动窗口最大值 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }

        int len = nums.length;

        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> x[0] != y[0] ? y[0] - x[0] : y[1] - x[1]);
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }

        int[] ans = new int[len - k + 1];
        ans[0] = queue.peek()[0];

        for (int i = k; i < len; i++) {
            queue.offer(new int[]{nums[i], i});
            while (!queue.isEmpty() && queue.peek()[1] <= i - k) {
                queue.poll();
            }

            ans[i - k + 1] = queue.peek()[0];
        }

        return ans;
    }
}
