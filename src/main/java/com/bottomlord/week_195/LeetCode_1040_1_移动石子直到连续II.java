package com.bottomlord.week_195;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-04-07 09:17:20
 */
public class LeetCode_1040_1_移动石子直到连续II {
    public int[] numMovesStonesII(int[] stones) {
        Arrays.sort(stones);
        int n = stones.length;
        int[] ans = new int[2];
        ans[1] = Math.max(stones[n - 1] - stones[1] + 1 - (n - 1), stones[n - 2] - stones[0] + 1 - (n - 1));

        int left = 0, right = 0;
        ans[0] = Integer.MAX_VALUE;
        while (right < n) {
            while (stones[right] - stones[left] + 1 > n) {
                left++;
            }

            if (right - left + 1 == n - 1 && stones[right] - stones[left] + 1 == n - 1) {
                ans[0] = Math.min(ans[0], 2);
            } else {
                ans[0] = Math.min(ans[0], n - (right - left + 1));
            }

            right++;
        }

        return ans;
    }
}
