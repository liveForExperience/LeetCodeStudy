package com.bottomlord.week_187;

import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2023-02-08 13:37:48
 */
public class LeetCode_2558_1_从数量最多的堆取走礼物 {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
        for (int gift : gifts) {
            queue.offer(gift);
        }

        while (k-- > 0 && !queue.isEmpty()) {
            Integer num = queue.poll();
            queue.offer((int)Math.sqrt(num));
        }

        long sum = 0;
        for (Integer num : queue) {
            sum += num;
        }
        return sum;
    }
}
