package com.bottomlord.week_002;

/**
 * @author LiveForExperience
 * @date 2019/7/20 15:14
 */
public class LeetCode_258_1_各位相加 {
    public int addDigits(int num) {
        int ans = 0;
        while(num > 0) {
            ans += num % 10;
            num /= 10;

            if(num == 0 && ans >= 10) {
                num = ans;
                ans = 0;
            }
        }

        return ans;
    }
}
