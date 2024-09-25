package com.bottomlord.week_078;

/**
 * @author ChenYue
 * @date 2021/1/5 19:18
 */
public class LeetCode_1712_1_将数组分成三个子数组的方案数 {
    public int waysToSplit(int[] nums) {
        int len = nums.length;

        int[] sum = new int[len];
        sum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        int target = sum[len - 1] / 3, mod =  1000000000 + 7;
        long ans = 0;
        for (int i = 0; i < len && sum[i] <= target; i++) {
            for (int j = i + 1; j < len; j++) {
                if (sum[i] <= sum[j] - sum[i] && sum[j] - sum[i] <= sum[len - 1] - sum[j]) {
                    ans++;
                }
            }
        }

        return (int)(ans % mod);
    }
}
