package com.bottomlord.week_4;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author LiveForExperience
 * @date 2019/7/30 20:33
 */
public class LeetCode_1046_1_最后一块石头的重量 {
    public int lastStoneWeight(int[] stones) {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : stones) {
            queue.offer(num);
        }

        while (queue.size() > 1) {
            int num = queue.poll() - queue.poll();
            if (num != 0) {
                queue.offer(num);
            }
        }

        return queue.isEmpty() ? 0 : queue.poll();
    }
}
