package com.bottomlord.contest_20220424;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-04-24 10:17:17
 */
public class Contest_1_1_多个数组求交集 {
    public List<Integer> intersection(int[][] nums) {
        int n = nums.length;
        if (n == 0) {
            return new ArrayList<>();
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums[0]) {
            set.add(num);
        }

        for (int i = 1; i < n; i++) {
            Set<Integer> curSet = new HashSet<>();
            for (int num : nums[i]) {
                if (set.contains(num)) {
                    curSet.add(num);
                }
            }

            set = curSet;
        }

        List<Integer> ans = new ArrayList<>(set);
        Collections.sort(ans);
        return ans;
    }
}
