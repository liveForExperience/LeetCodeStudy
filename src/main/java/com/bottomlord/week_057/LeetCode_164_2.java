package com.bottomlord.week_057;

/**
 * @author ChenYue
 * @date 2020/8/4 8:31
 */
public class LeetCode_164_2 {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int t = Math.max(1, (max - min) / (n - 1)), s = (max - min) / t + 1;
        int[][] buckets = new int[s][3];
        for (int[] bucket : buckets) {
            bucket[0] = max;
            bucket[1] = min;
            bucket[2] = -1;
        }

        for (int num : nums) {
            int i = (num - min) / t;
            buckets[i][0] = Math.min(buckets[i][0], num);
            buckets[i][1] = Math.max(buckets[i][1], num);
            buckets[i][2] = 1;
        }

        int ans = 0, pre = min;
        for (int[] bucket : buckets) {
            if (bucket[2] == -1) {
                continue;
            }

            ans = Math.max(ans, bucket[0] - pre);
            pre = bucket[1];
        }
        return ans;
    }
}
