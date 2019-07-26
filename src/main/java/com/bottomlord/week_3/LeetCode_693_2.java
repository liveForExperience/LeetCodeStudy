package com.bottomlord.week_3;

/**
 * @author LiveForExperience
 * @date 2019/7/26 22:14
 */
public class LeetCode_693_2 {
    public boolean hasAlternatingBits(int n) {
        while (n > 1) {
            if ((n & 1) == ((n >>= 1) & 1)) {
                return false;
            }
        }
        return true;
    }
}