package com.bottomlord.week_189;

import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2023-02-22 20:22:18
 */
public class LeetCode_1792_1_最大平均通过率 {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> {
            double diff = increasment(y) - increasment(x);
            if (diff == 0) {
                return 0;
            }
            return diff > 0 ? 1 : -1;
        });

        for (int[] aClass : classes) {
            queue.offer(aClass);
        }

        for (int i = 0; i < extraStudents; i++) {
            int[] aClass = queue.poll();
            if (aClass == null) {
                continue;
            }

            aClass[0]++;
            aClass[1]++;
            queue.offer(aClass);
        }

        double sum = 0, size = queue.size();
        while (!queue.isEmpty()) {
            int[] aClass = queue.poll();
            sum += 1D * aClass[0] / aClass[1];
        }

        return sum / size;
    }

    private double increasment(int[] x) {
        return (1D * x[0] + 1) / (x[1] + 1) - 1D * x[0] / x[1];
    }
}
