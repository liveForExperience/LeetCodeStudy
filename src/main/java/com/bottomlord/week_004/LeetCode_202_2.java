package com.bottomlord.week_004;

/**
 * @author LiveForExperience
 * @date 2019/8/2 13:12
 */
public class LeetCode_202_2 {
    public boolean isHappy(int n) {
        while (n != 1) {
            int sum = 0;
            while (n > 0) {
                sum += Math.pow(n % 10, 2);
                n /= 10;
            }
            if (sum == 3 || sum == 4) {
                return false;
            }
            n = sum;
        }
        return true;
    }
}
