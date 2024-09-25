package com.bottomlord.week_078;

/**
 * @author ChenYue
 * @date 2021/1/5 19:41
 */
public class LeetCode_1712_2 {
    public int waysToSplit(int[] nums) {
        int len = nums.length, mod = 1000000007;
        long ans = 0;
        int[] sum = new int[len];
        sum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        for (int i = 0; i < len; i++) {
            if (sum[i] * 3 > sum[len - 1]) {
                break;
            }

            int minL = i + 1, minR = len - 2;
            while (minL <= minR) {
                int mid = minL + (minR - minL) / 2;

                if (sum[mid] - sum[i] >= sum[i]) {
                    minR = mid - 1;
                } else {
                    minL = mid + 1;
                }
            }

            int maxL = i + 1, maxR = len - 2;
            while (maxL <= maxR) {
                int mid = maxL + (maxR - maxL) / 2;

                if (sum[mid] - sum[i] <= sum[len - 1] - sum[mid]) {
                    maxL = mid + 1;
                } else {
                    maxR = mid - 1;
                }
            }

            ans += maxR - minL + 1;
            ans %= mod;
        }

        return (int)ans % mod;
    }
}
