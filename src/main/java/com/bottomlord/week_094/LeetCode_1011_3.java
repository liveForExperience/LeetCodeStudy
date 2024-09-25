package com.bottomlord.week_094;

/**
 * @author ChenYue
 * @date 2021/4/26 10:01
 */
public class LeetCode_1011_3 {
    public int shipWithinDays(int[] weights, int D) {
        int max = Integer.MIN_VALUE;
        for (int weight : weights) {
            max = Math.max(max, weight);
        }

        int head = max, tail = max * weights.length / D;
        while (head < tail) {
            int mid = head + (tail - head) >>> 1;

            if (isValid(weights, mid, D)) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }

        return head;
    }

    private boolean isValid(int[] weights, int target, int day) {
        int cur = target;

        for (int i = 0; i < weights.length;) {
            int weight = weights[i];

            if (cur - weight < 0) {
                cur = target;
                day--;
            } else {
                cur -= weight;
                i++;
            }

            if (day <= 0) {
                return false;
            }
        }

        return true;
    }
}
