package com.bottomlord.week_001;

/**
 * @author LiveForExperience
 * @date 2019/7/14 14:22
 */
public class LeetCode_476_1_数字的补数 {
    public int findComplement(int num) {
        int tmp = 1;
        while (tmp < num) {
            tmp <<= 1;
            tmp += 1;
        }

        return tmp ^ num;
    }
}
