package com.bottomlord.week_228;

import java.util.Collections;
import java.util.List;

import static com.bottomlord.LeetCodeUtils.convertIntList;

/**
 * @author chen yue
 * @date 2023-11-24 11:23:23
 */
public class LeetCode_2824_2 {
    public int countPairs(List<Integer> nums, int target) {
        Collections.sort(nums);
        int n = nums.size(), ans = 0;
        for (int i = 0; i < n; i++) {
            int head = i, tail = n - 1, cur = i;
            while (head <= tail) {
                int mid = head + (tail - head) / 2;
                int num = nums.get(mid);

                if (num + nums.get(i) >= target) {
                    tail = mid - 1;
                } else {
                    cur = mid;
                    head = mid + 1;
                }
            }

            ans += cur - i;
        }

        return ans;
    }

    public static void main(String[] args) {
        LeetCode_2824_2 t = new LeetCode_2824_2();
        t.countPairs(convertIntList("[-6,2,5,-2,-7,-1,3]"), 2);
    }
}