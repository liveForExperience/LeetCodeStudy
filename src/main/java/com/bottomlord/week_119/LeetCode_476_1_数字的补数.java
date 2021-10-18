package com.bottomlord.week_119;

/**
 * @author chen yue
 * @date 2021-10-18 09:02:10
 */
public class LeetCode_476_1_数字的补数 {
    public int findComplement(int num) {
        int highBit = 0;
        for (int i = 1; i <= 30; i++) {
            if (num >= 1 << i) {
                highBit = i;
            } else {
                break;
            }
        }

        int mask = highBit == 30 ? 0x7fffffff : (1 << (highBit + 1)) - 1;
        return mask ^ num;
    }
}
