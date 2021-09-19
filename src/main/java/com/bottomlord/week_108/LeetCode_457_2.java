package com.bottomlord.week_108;

/**
 * @author chen yue
 * @date 2021-08-07 14:54:32
 */
public class LeetCode_457_2 {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        int[] memo = new int[n + 1];
        for (int i = 0, idx = 1; i < n; i++, idx++) {
            if (memo[i] > 0) {
                continue;
            }

            int k = 0, cur = i;
            boolean flag = nums[cur] > 0;
            while (k <= n) {
                int next = ((cur + nums[cur]) %  n + n) % n;
                if (cur == next) {
                    break;
                }

                if (flag && nums[next] < 0) {
                    break;
                }

                if (!flag && nums[next] > 0) {
                    break;
                }

                if (memo[next] > 0) {
                    if (memo[next] != idx) {
                        break;
                    }

                    return true;
                }

                k++;
                cur = next;
                memo[next] = idx;
            }
        }

        return false;
    }
}
