package com.bottomlord.week_112;

import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2021-09-03 08:20:20
 */
public class LeetCode_interview_1714_1_最小k个数 {
    public int[] smallestK(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : arr) {
            queue.offer(num);
        }

        k = Math.min(k, arr.length);
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] =  queue.poll();
        }

        return ans;
    }
}
