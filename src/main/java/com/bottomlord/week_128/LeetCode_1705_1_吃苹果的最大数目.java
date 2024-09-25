package com.bottomlord.week_128;

import java.util.*;

/**
 * @author chen yue
 * @date 2021-12-24 08:55:32
 */
public class LeetCode_1705_1_吃苹果的最大数目 {
    public int eatenApples(int[] apples, int[] days) {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        int n = apples.length, index = 0, count = 0;
        while (index < n || !queue.isEmpty()) {
            if (index < n && apples[index] > 0) {
                queue.offer(new int[]{index + days[index], apples[index]});
            }

            while (!queue.isEmpty() && queue.peek()[0] <= index) {
                queue.poll();
            }

            if (!queue.isEmpty()) {
                int[] arr = queue.poll();
                arr[1]--;
                count++;
                if (arr[1] > 0 && index < arr[0]) {
                    queue.offer(arr);
                }
            }

            index++;
        }

        return count;
    }
}
