package com.bottomlord.week_024;

public class LeetCode_1011_1_在D天内送达包裹的能力 {
    public int shipWithinDays(int[] weights, int D) {
        int num = Integer.MIN_VALUE;
        for (int weight : weights) {
            num = Math.max(weight, num);
        }

        while (true) {
            if (isValid(weights, num, D)) {
                break;
            } else {
                num++;
            }
        }

        return num;
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
