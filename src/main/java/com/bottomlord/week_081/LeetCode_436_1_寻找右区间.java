package com.bottomlord.week_081;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2021/1/28 8:23
 */
public class LeetCode_436_1_寻找右区间 {
    public int[] findRightInterval(int[][] intervals) {
        int len = intervals.length;
        PriorityQueue<int[]> startQueue = new PriorityQueue<>(Comparator.comparingInt(x -> x[0])),
                             endQueue = new PriorityQueue<>(Comparator.comparingInt(x -> x[1]));
        int[] ans = new int[len];
        Arrays.fill(ans, -1);

        for (int i = 0; i < intervals.length; i++) {
            int[] arr = new int[3];
            arr[0] = intervals[i][0];
            arr[1] = intervals[i][1];
            arr[2] = i;

            startQueue.offer(arr);
            endQueue.offer(arr);
        }

        while (!endQueue.isEmpty()) {
            int[] endArr = endQueue.poll();
            while (!startQueue.isEmpty()) {
                int[] startArr = startQueue.peek();
                if (startArr[0] >= endArr[1]) {
                    ans[endArr[2]] = startArr[2];
                    break;
                }

                startQueue.poll();
            }
        }

        return ans;
    }
}
