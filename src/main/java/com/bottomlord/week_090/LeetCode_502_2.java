package com.bottomlord.week_090;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author ChenYue
 * @date 2021/3/26 8:51
 */
public class LeetCode_502_2 {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        boolean speedUp = true;
        int sum = W;
        for (int i = 0; i < Capital.length; i++) {
            if (W < Capital[i]) {
                speedUp = false;
                break;
            }
        }

        if (speedUp) {
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            for (int i = 1; i <= Capital.length; i++) {
                queue.offer(Profits[i]);

                if (i > k) {
                    queue.poll();
                }
            }

            return queue.stream().mapToInt(x -> x).sum();
        }

        for (int i = 0; i < Math.min(k, Profits.length); i++) {
            int index = -1;

            for (int j = 0; j < Profits.length; j++) {
                if (W >= Capital[j]) {
                    if (index == -1 || Profits[index] < Profits[j]) {
                        index = j;
                    }
                }
            }

            if (index == -1) {
                break;
            }

            W += Profits[index];
            Capital[index] = Integer.MAX_VALUE;
        }

        return W;
    }
}
