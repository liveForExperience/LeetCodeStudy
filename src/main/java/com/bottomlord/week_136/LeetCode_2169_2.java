package com.bottomlord.week_136;

/**
 * @author chen yue
 * @date 2022-02-15 09:03:38
 */
public class LeetCode_2169_2 {
    public int countOperations(int num1, int num2) {
        int ans = 0;
        while (num1 > 0 && num2 > 0) {
            if (num1 >= num2) {
                ans += num1 / num2;
                num1 %= num2;
            } else {
                ans += num2 / num1;
                num2 %= num1;
            }
        }

        return ans;
    }
}