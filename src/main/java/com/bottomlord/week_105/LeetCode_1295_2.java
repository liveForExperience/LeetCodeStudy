package com.bottomlord.week_105;

/**
 * @author ChenYue
 * @date 2021/7/12 8:49
 */
public class LeetCode_1295_2 {
    public int findNumbers(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            if (judge(num)) {
                ans++;
            }
        }
        return ans;
    }

    private boolean judge(int num) {
        int count = 0;
        while (num > 0) {
            count++;
            num /= 10;
        }

        return count % 2 == 0;
    }
}
