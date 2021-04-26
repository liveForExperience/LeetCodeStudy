package com.bottomlord.week_094;

/**
 * @author ChenYue
 * @date 2021/4/26 8:30
 */
public class LeetCode_1011_1_在D天内送达包裹的能力 {
    public int shipWithinDays(int[] weights, int D) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int num : weights) {
            max = Math.max(max, num);
            sum += num;
        }

        int avg = sum % D == 0 ? sum / D : sum / D + 1;
        int target = Math.max(avg, max);

        boolean find = false;
        while (!find) {
            find = backTrack(weights, 0, 0, target, D);
            if (!find) {
                target++;
            }
        }

        return target;
    }

    private boolean backTrack(int[] weights, int index, int count, int target, int day) {
        if (count > day) {
            return false;
        }

        if (index == weights.length) {
            return true;
        }

        int sum = 0;
        boolean find = false;
        for (int i = index; i < weights.length; i++) {
            sum += weights[i];
            if (sum > target) {
                break;
            }
            find = backTrack(weights, i + 1, count + 1, target, day);

            if (find) {
                return true;
            }
        }

        return false;
    }
}
