package com.bottomlord.week_228;

import java.util.List;

/**
 * @author chen yue
 * @date 2023-11-24 11:13:08
 */
public class LeetCode_2824_1_统计和小于目标的下标对数目 {
    public int countPairs(List<Integer> nums, int target) {
        int ans = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(i) + nums.get(j) < target) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
