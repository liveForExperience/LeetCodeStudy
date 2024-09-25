package com.bottomlord.week_094;

/**
 * @author ChenYue
 * @date 2021/4/26 9:25
 */
public class LeetCode_1011_2 {
    public int shipWithinDays(int[] weights, int D) {
        int sum = 0, max = Integer.MIN_VALUE;
        for (int weight : weights) {
            sum += weight;
            max = Math.max(max, weight);
        }

        int head = max, tail = sum;
        while (head < tail) {
            int mid = head + (tail - head) / 2;

            if (isValid(weights, mid, D)) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }

        return head;
    }

    private boolean isValid(int[] weights, int target, int day) {
        int sum = target;
        for (int i = 0; i < weights.length;) {
            int weight = weights[i];

            if (sum - weight < 0) {
                sum = target;
                day--;
            } else {
                sum -= weight;
                i++;
            }

            if (day <= 0) {
                return false;
            }
        }

        return true;
    }
}
