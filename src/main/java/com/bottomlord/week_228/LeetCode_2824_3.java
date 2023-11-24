package com.bottomlord.week_228;

import java.util.Collections;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-11-24 19:39:14
 */
public class LeetCode_2824_3 {
    public int countPairs(List<Integer> nums, int target) {
        Collections.sort(nums);
        int n = nums.size(), ans = 0;
        for (int head = 0, tail = n - 1; head < tail; head++) {
            while (head < tail && nums.get(head) + nums.get(tail) >= target) {
                tail--;
            }

            ans += tail - head;
        }

        return ans;
    }
}