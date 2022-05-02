package com.bottomlord.contest_20220501;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author chen yue
 * @date 2022-05-01 00:34:30
 */
public class Contest_3_1_含最多k个可整除元素的子数组 {
    public int countDistinct(int[] nums, int k, int p) {
        int len = nums.length, count = 0;
        Set<String> set = new HashSet<>();

        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (check(nums, i, j, k, p)) {
                    String cur = Arrays.toString(Arrays.copyOfRange(nums, i, j - i + 1));
                    if (set.add(cur)) {
                        count++;
                    }
                }
            }
        }

        System.out.println(set);
        return count;
    }

    private boolean check(int[]nums, int start, int end, int k, int p) {
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (nums[i] % p == 0) {
                count++;
            }

            if (count > k) {
                return false;
            }
        }

        return true;
    }
}
