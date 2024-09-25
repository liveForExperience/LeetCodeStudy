package com.bottomlord.week_095;

import java.util.Arrays;

public class LeetCode_LCP33_1_蓄水 {
    public int storeWater(int[] bucket, int[] vat) {
        if (Arrays.stream(vat).sum() == 0) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < 1000; i++) {
            int sum = i;
            for (int j = 0; j < bucket.length; j++) {
                if (vat[j] == 0) {
                    continue;
                }

                int left = vat[i] - i * bucket[i];
                if (left <= 0) {
                    continue;
                }

                sum += left % i == 0 ? left / i : left / i + 1;
            }
            ans = Math.min(ans, sum);
        }

        return ans;
    }
}
