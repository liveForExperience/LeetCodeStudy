package com.bottomlord.week_006;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class LeetCode_628_1_三个数的最大乘积 {
    public int maximumProduct(int[] nums) {
        Queue<Integer> maxQ = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> minQ = new PriorityQueue<>();

        for (int num : nums) {
            maxQ.offer(num);
            minQ.offer(num);
        }

        int[] arr = new int[6];
        int count = 3, index = 0, negative = 0, ans = 0;
        while (count-- > 0) {
            int max = maxQ.poll();
            if (max < 0) {
                negative++;
            }
            arr[index] = max;

            int min = minQ.poll();
            if (min < 0) {
                negative++;
            }
            arr[index + 3] = min;
            index++;
        }

        if (negative == 5) {
            return arr[3] * arr[4] * arr[0];
        }

        return Math.max(arr[0] * arr[1] * arr[2], arr[0] * arr[3] * arr[4]);
    }
}
