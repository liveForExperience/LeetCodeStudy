package com.bottomlord.week_024;

public class LeetCode_1011_2 {
    public int shipWithinDays(int[] weights, int D) {
        int max = Integer.MIN_VALUE, sum = 0;
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

    private boolean isValid(int[] weights, int num, int day) {
        int cur = num;

        for (int i = 0; i < weights.length;) {
            if (weights[i] > num) {
                return false;
            }

            if (cur - weights[i] < 0) {
                cur = num;
                day--;
            } else {
                cur -= weights[i];
                i++;
            }

            if (day <= 0) {
                return false;
            }
        }

        return true;
    }
}