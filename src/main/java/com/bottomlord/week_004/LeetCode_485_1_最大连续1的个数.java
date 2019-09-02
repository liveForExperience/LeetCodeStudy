package com.bottomlord.week_004;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author LiveForExperience
 * @date 2019/8/2 21:06
 */
public class LeetCode_485_1_最大连续1的个数 {
    public int findMaxConsecutiveOnes(int[] nums) {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count++;
            }

            if (num == 0) {
                queue.offer(count);
                count = 0;
            }
        }

        if (count != 0) {
            queue.offer(count);
        }

        return queue.isEmpty() ? 0 : queue.poll();
    }
}
