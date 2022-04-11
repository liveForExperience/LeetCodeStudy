package com.bottomlord.contest_20220410;

import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2022-04-10 11:03:14
 */
public class Contest_3_1_K次增加后的最大乘积 {
    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
        }

        while (k-- > 0 && !queue.isEmpty()) {
            int num = queue.poll();
            num++;
            queue.offer(num);
        }

        long ans = 1;
        while (!queue.isEmpty()) {
            ans *= queue.poll();
            ans %= 1000000007;
        }

        return (int)ans;
    }
}
