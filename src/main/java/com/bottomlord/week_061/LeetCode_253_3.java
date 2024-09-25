package com.bottomlord.week_061;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2020/9/4 8:54
 */
public class LeetCode_253_3 {
    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing(x -> x[0]));
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int[] interval : intervals) {
            if (queue.isEmpty() || queue.peek() <= interval[0]) {
                queue.poll();
            }
            queue.offer(interval[1]);
        }

        return queue.size();
    }
}