package com.bottomlord.week_202;

/**
 * @author chen yue
 * @date 2023-05-27 22:18:36
 */
public class LeetCode_1093_1_大样本统计 {
    public double[] sampleStats(int[] count) {
        double[] ans = new double[]{Double.MAX_VALUE, Double.MIN_VALUE, 0, 0, 0};
        boolean find = false;
        long sum = 0, n = 0, max = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) {
                continue;
            }

            if (!find) {
                ans[0] = i;
                find = true;
            }

            ans[1] = i;

            sum += i * 1D * count[i];
            n += count[i];

            if (count[i] > max) {
                max = count[i];
                ans[4] = i;
            }
        }

        long target = n % 2 == 0 ? n / 2 : n / 2 + 1, cur = 0;
        boolean findOne = false, findTwo = n % 2 == 1;
        long one = 0, two = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 0) {
                continue;
            }

            cur += count[i];
            if (!findOne && cur >= target) {
                if (!findOne) {
                    one = i;
                    findOne = true;
                }

                if (!findTwo && cur >= target + 1) {
                    two = i;
                    findTwo = true;
                }
            }

            if (!findTwo && cur >= target + 1) {
                two = i;
                findTwo = true;
            }
        }
        ans[2] = sum * 1D / n;
        ans[3] = n % 2 == 0 ? (one + two) * 1D / 2 : one;
        return ans;
    }
}
