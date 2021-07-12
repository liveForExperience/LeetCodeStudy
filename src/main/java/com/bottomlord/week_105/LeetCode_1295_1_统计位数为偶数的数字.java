package com.bottomlord.week_105;

/**
 * @author ChenYue
 * @date 2021/7/12 8:39
 */
public class LeetCode_1295_1_统计位数为偶数的数字 {
    public int findNumbers(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            String str = Integer.toString(num);
            ans += str.length() % 2 == 0 ? 1 : 0;
        }
        return ans;
    }
}
