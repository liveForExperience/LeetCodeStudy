package com.bottomlord.week_239;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2024-02-06 20:39:13
 */
public class LeetCode_LCP30_1_魔塔游戏 {
    public int magicTower(int[] nums) {
        int sum = 0, cnt = 0;
        Queue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            sum += num;

            if (num < 0) {
                queue.offer(num);
            }

            if (sum < 0) {
                cnt++;

                if (queue.isEmpty()) {
                    return -1;
                }

                sum -= queue.poll();
            }
        }

        return sum < 0 ? -1 : cnt;
    }
}
