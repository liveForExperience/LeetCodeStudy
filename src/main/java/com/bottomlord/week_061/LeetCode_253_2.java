package com.bottomlord.week_061;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2020/9/4 8:41
 */
public class LeetCode_253_2 {
    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparing(x -> x[0]));
        for (int[] interval : intervals) {
            queue.offer(interval);
        }

        List<int[]> list = new LinkedList<>();
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (list.isEmpty()) {
                list.add(cur);
                continue;
            }

            boolean flag = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i)[1] <= cur[0]) {
                    list.set(i, cur);
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                list.add(cur);
            }
        }

        return list.size();
    }
}
