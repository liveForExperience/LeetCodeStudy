package com.bottomlord.week_6;

public class LeetCode_717_1_1比特与2比特字符 {
    public boolean isOneBitCharacter(int[] bits) {
        boolean isOne = false, isTwo = false;
        for (int i = 0; i < bits.length;) {
            if (bits[i] == 0) {
                i++;
                isOne = true;
                isTwo = false;
            } else {
                i += 2;
                isOne = false;
                isTwo = true;
            }
        }

        return isOne;
    }
}
