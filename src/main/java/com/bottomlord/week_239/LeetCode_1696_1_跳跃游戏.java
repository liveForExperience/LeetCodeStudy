package com.bottomlord.week_239;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2024-02-07 17:00:42
 */
public class LeetCode_1696_1_跳跃游戏 {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Queue<Integer> queue = new PriorityQueue<>((x, y) -> dp[y] - dp[x]);
        dp[0] = nums[0];
        queue.offer(0);

        for (int i = 1; i < n; i++) {
            while (!queue.isEmpty() && queue.peek() < i - k) {
                queue.poll();
            }

            if (queue.isEmpty()) {
                break;
            }

            int index = queue.peek();
            dp[i] = nums[i] + dp[index];

            queue.offer(i);
        }

        return dp[n - 1];
    }
}
