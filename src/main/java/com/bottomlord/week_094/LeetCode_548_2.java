package com.bottomlord.week_094;

/**
 * @author ChenYue
 * @date 2021/4/27 15:33
 */
public class LeetCode_548_2 {
    public boolean splitArray(int[] nums) {
        int len = nums.length;
        int[] sums = new int[len];
        sums[0] = nums[0];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int i = 1; i < len; i++) {
            int one = sums[i - 1];
            for (int j = i + 1; j < len; j++) {
                int two = sums[j - 1] - sums[i];
                if (one != two) {
                    continue;
                }
                for (int k = j + 1; k < len; k++) {
                    int three = sums[k - 1] - sums[j],
                        four = sums[len - 1] - sums[k];

                    if (two == three && three == four) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
