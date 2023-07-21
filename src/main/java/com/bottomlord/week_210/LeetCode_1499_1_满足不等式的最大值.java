package com.bottomlord.week_210;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author chen yue
 * @date 2023-07-21 22:28:36
 */
public class LeetCode_1499_1_满足不等式的最大值 {
    public int findMaxValueOfEquation(int[][] points, int k) {
        Deque<int[]> deque = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;
        for (int[] point : points) {
            int x = point[0], y = point[1];
            while (!deque.isEmpty() && deque.peekFirst()[0] < x - k) {
                deque.pollFirst();
            }

            if (!deque.isEmpty()) {
                max = Math.max(max, x + y + deque.peekFirst()[1]);
            }

            while (!deque.isEmpty() && y - x >= deque.peekLast()[1]) {
                deque.pollLast();
            }
            deque.offerLast(new int[]{x, y - x});
        }
        return max;
    }
}
