package com.bottomlord.week_015;

import java.util.HashSet;
import java.util.Set;

public class LeetCode_421_1_数组中两个数的最大异或值 {
    public int findMaximumXOR(int[] nums) {
        int mask = 0, ans = 0;
        for (int i = 31; i >= 0; i--) {
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for (int num : nums) {
                set.add(num & mask);
            }

            int tmp = ans | (1 << i);
            for (int num : set) {
                if (set.contains(num ^ tmp)) {
                    ans = tmp;
                    break;
                }
            }
        }

        return ans;
    }
}