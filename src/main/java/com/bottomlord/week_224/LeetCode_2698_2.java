package com.bottomlord.week_224;

/**
 * @author chen yue
 * @date 2023-10-25 13:29:22
 */
public class LeetCode_2698_2 {
    public int punishmentNumber(int n) {
        int sum = 0;
        for (int target = 1; target <= n; target++) {
            int pow = target * target;
            if (backTrack(pow, target)) {
                sum += pow;
            }
        }

        return sum;
    }

    private boolean backTrack(int pow, int target) {
        if (pow < target) {
            return false;
        }

        if (pow == target) {
            return true;
        }

        int mask = 10;
        while (pow >= mask && pow % mask <= target) {
            if (backTrack(pow / mask, target - (pow % mask))) {
                return true;
            }

            mask *= 10;
        }

        return false;
    }
}
