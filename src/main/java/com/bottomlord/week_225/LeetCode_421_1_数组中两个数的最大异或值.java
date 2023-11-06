package com.bottomlord.week_225;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2023-11-04 13:38:55
 */
public class LeetCode_421_1_数组中两个数的最大异或值 {
    public int findMaximumXOR(int[] nums) {
        int mask = 0, ans = 0;
        for (int i = 31; i >= 0; i--) {
            Set<Integer> memo = new HashSet<>();
            mask |= 1 << i;

            int target = ans | 1 << i;

            for (int num : nums) {
                num &= mask;

                if (memo.contains(target ^ num)) {
                    ans = target;
                    break;
                }

                memo.add(num);
            }
        }

        return ans;
    }
}
