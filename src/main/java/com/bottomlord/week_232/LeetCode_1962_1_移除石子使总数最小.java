package com.bottomlord.week_232;

import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2023-12-23 14:27:46
 */
public class LeetCode_1962_1_移除石子使总数最小 {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
        for (int pile : piles) {
            queue.offer(pile);
        }

        while (k-- > 0) {
            Integer num = queue.poll();
            if (num == null) {
                break;
            }

            queue.offer(num - num / 2);
        }

        int sum = 0;
        while (!queue.isEmpty()) {
            sum += queue.poll();
        }

        return sum;
    }
}
