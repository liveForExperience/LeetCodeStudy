package com.bottomlord.week_213;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2023-07-31 19:11:34
 */
public class LeetCode_2532_1_过桥时间 {
    public int findCrossingTime(int n, int k, int[][] time) {
        Comparator<Integer> waitComparator = (x, y) -> {
            int waitX = time[x][0] + time[x][2], waitY = time[y][0] + time[y][2];
            return waitX != waitY ? waitY - waitX : y - x;
        };

        Comparator<int[]> workComparator = (x, y) -> x[0] != y[0] ? x[0] - y[0] : x[1] - y[1];

        PriorityQueue<Integer> waitRight = new PriorityQueue<>(waitComparator),
                               waitLeft = new PriorityQueue<>(waitComparator);
        PriorityQueue<int[]> workRight = new PriorityQueue<>(workComparator),
                             workLeft = new PriorityQueue<>(workComparator);

        for (int i = 0; i < k; i++) {
            waitLeft.offer(i);
        }

        int curTime = 0;
        while (n > 0 || !workRight.isEmpty() || !waitRight.isEmpty()) {
            while (!workRight.isEmpty() && workRight.peek()[0] <= curTime) {
                waitRight.offer(workRight.poll()[1]);
            }

            while (!workLeft.isEmpty() && workLeft.peek()[0] <= curTime) {
                waitLeft.offer(workLeft.poll()[1]);
            }

            if (!waitRight.isEmpty()) {
                int id = waitRight.poll();
                int[] worker = time[id];
                curTime += worker[2];
                workLeft.offer(new int[]{curTime + worker[3], id});
            } else if (n > 0 && !waitLeft.isEmpty()) {
                int id = waitLeft.poll();
                int[] worker = time[id];
                curTime += worker[0];
                workRight.offer(new int[]{curTime + worker[1], id});
                n--;
            } else {
                int nextTime = Integer.MAX_VALUE;
                if (!workLeft.isEmpty()) {
                    nextTime = Math.min(nextTime, workLeft.peek()[0]);
                }

                if (!workRight.isEmpty()) {
                    nextTime = Math.min(nextTime, workRight.peek()[0]);
                }

                if (nextTime != Integer.MAX_VALUE) {
                    curTime = Math.max(curTime, nextTime);
                }
            }
        }

        return curTime;
    }

    public static void main(String[] args) {
        LeetCode_2532_1_过桥时间 t = new LeetCode_2532_1_过桥时间();
        t.findCrossingTime(1, 3, new int[][]{{1,1,2,1},{1,1,3,1},{1,1,4,1}});
    }
}
