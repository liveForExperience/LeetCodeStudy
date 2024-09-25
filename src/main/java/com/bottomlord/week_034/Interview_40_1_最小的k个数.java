package com.bottomlord.week_034;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ThinkPad
 * @date 2020/2/28 12:41
 */
public class Interview_40_1_最小的k个数 {
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] ans = new int[k];
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : arr) {
            queue.offer(num);
        }

        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll();
        }

        return ans;
    }
}
