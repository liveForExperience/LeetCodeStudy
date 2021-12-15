package com.bottomlord.week_127;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2021-12-14 08:50:40
 */
public class LeetCode_690_1_课程表III {
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt(x -> x[1]));
        Queue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);

        int start = 0;
        for (int[] course : courses) {
            int duration = course[0], end = course[1];

            start += duration;
            queue.offer(duration);

            if (start > end && !queue.isEmpty()) {
                start -= queue.poll();
            }
        }

        return queue.size();
    }
}
