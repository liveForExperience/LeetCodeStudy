package com.bottomlord.week_223;

import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2023-10-18 11:11:29
 */
public class LeetCode_2530_1_执行K次操作后的最大分数 {
    public long maxKelements(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> nums[y] - nums[x]);
        for (int i = 0; i < n; i++) {
            queue.offer(i);
        }

        long sum = 0;
        while (k-- > 0 && !queue.isEmpty()) {
            int index = queue.poll();
            sum += nums[index];
            nums[index] = (int) Math.ceil(1D * nums[index] / 3);
            queue.offer(index);
        }

        return sum;
    }
}
