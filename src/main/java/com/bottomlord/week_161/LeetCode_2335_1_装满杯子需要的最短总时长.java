package com.bottomlord.week_161;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2022-08-11 08:28:56
 */
public class LeetCode_2335_1_装满杯子需要的最短总时长 {
    public int fillCups(int[] amount) {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int num : amount) {
            queue.offer(num);
        }

        int count = 0;
        while (queue.size() > 1) {
            int one = queue.poll(), two = queue.poll();
            if (one == 0) {
                count += two;
                continue;
            }

            if (two == 0) {
                count += one;
                continue;
            }

            one --;
            two --;

            if (one != 0) {
                queue.offer(one);
            }

            if (two != 0) {
                queue.offer(two);
            }

            count++;
        }

        if (!queue.isEmpty()) {
            count += queue.poll();
        }

        return count;
    }
}
