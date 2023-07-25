package com.bottomlord.week_211;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2023-07-25 18:31:55
 */
public class LeetCode_2208_1_将数组和减半的最少操作次数 {
    public int halveArray(int[] nums) {
        Queue<Double> queue = new PriorityQueue<>((x, y) -> Double.compare(y, x));
        double sum = 0D;
        for (int num : nums) {
            queue.offer(num * 1D);
            sum += num;
        }

        double target = sum / 2;
        int count = 0;
        while (!queue.isEmpty() && sum > target) {
            double cur = queue.poll();
            sum -= cur / 2;
            queue.offer(cur / 2);
            count++;
        }

        return count;
    }
}
