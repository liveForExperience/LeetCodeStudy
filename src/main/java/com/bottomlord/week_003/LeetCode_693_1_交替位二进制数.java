package com.bottomlord.week_003;

/**
 * @author LiveForExperience
 * @date 2019/7/26 22:02
 */
public class LeetCode_693_1_交替位二进制数 {
    public boolean hasAlternatingBits(int n) {
        char[] cs = Integer.toBinaryString(n).toCharArray();;
        for (int i = 1; i < cs.length; i++) {
            if (cs[i] == cs[i - 1]) {
                return false;
            }
        }

        return true;
    }
}
