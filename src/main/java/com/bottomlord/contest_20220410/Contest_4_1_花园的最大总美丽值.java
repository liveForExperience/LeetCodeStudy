package com.bottomlord.contest_20220410;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-04-10 11:12:17
 */
public class Contest_4_1_花园的最大总美丽值 {
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        Arrays.sort(flowers);
        long n = flowers.length;

        if (flowers[0] >= target) {
            return full * n;
        }

        long fullStatus = n * target;
        long curFlowers = 0;
        for (int flower : flowers) {
            curFlowers += Math.min(flower, target);
        }

        long leftFlowers = curFlowers + newFlowers - fullStatus;
        long sumFlowers = 0, max = 0;
        for (int i = 0, x = 0; i < n; i++) {
            if (leftFlowers >= 0) {
                while (x < i && (long) x * flowers[x] - sumFlowers <= leftFlowers) {
                    sumFlowers += flowers[x++];
                }

                long beauty = (n - i) * full;
                if (x > 0) {
                    beauty += Math.min(target - 1, (leftFlowers + sumFlowers) / x) * partial;
                }
                max = Math.max(max, beauty);
            }

            leftFlowers += target - Math.min(flowers[i], target);
        }

        return max;
    }
}
