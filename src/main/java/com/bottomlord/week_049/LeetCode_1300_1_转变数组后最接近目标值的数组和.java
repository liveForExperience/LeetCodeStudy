package com.bottomlord.week_049;

/**
 * @author ChenYue
 * @date 2020/6/14 20:27
 */
public class LeetCode_1300_1_转变数组后最接近目标值的数组和 {
    public int findBestValue(int[] arr, int target) {
        int len = arr.length, sum = 0, max = Integer.MIN_VALUE;
        for (int num : arr) {
            sum += num;
            max = Math.max(max, num);
        }

        if (sum <= target) {
            return max;
        }

        int avg = sum / len;
        sum = sum(arr, avg);

        while (sum < target) {
            int tmp = sum(arr, avg + 1);
            if (tmp >= target) {
                return target - sum <= tmp - target ? avg : avg + 1;
            }
            sum = tmp;
            avg++;
        }

        return 0;
    }

    private int sum(int[] arr, int avg) {
        int sum = 0;
        for (int num : arr) {
            sum += Math.min(num, avg);
        }
        return sum;
    }
}
