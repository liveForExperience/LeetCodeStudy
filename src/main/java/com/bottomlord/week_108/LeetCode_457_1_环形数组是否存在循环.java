package com.bottomlord.week_108;

public class LeetCode_457_1_环形数组是否存在循环 {
    public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (check(nums, i)) {
                return true;
            }
        }

        return false;
    }

    private boolean check(int[] nums, int start) {
        int cur = start, k = 0, n = nums.length;
        boolean flag = nums[cur] > 0;

        while (k < n) {
            int next = ((cur + nums[cur]) % n + n) % n;
            if (flag && nums[next] < 0) {
                return false;
            }

            if (!flag && nums[next] > 0) {
                return false;
            }

            if (next == start) {
                return k > 0;
            }

            cur = next;
            k++;
        }

        return false;
    }
}
