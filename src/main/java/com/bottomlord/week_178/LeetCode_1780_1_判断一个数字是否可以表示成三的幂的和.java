package com.bottomlord.week_178;

/**
 * @author chen yue
 * @date 2022-12-09 15:55:03
 */
public class LeetCode_1780_1_判断一个数字是否可以表示成三的幂的和 {
    public boolean checkPowersOfThree(int n) {
        while (n != 0) {
            if (n % 3 == 0 || n % 3 == 1) {
                n /= 3;
            } else {
                return false;
            }
        }

        return true;
    }
}
