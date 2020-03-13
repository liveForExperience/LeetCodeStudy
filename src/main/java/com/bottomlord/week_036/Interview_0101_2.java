package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/13 8:45
 */
public class Interview_0101_2 {
    public boolean isUnique(String astr) {
        int check = 0;
        for (char c : astr.toCharArray()) {
            int mask = 1 << c;
            if ((check & mask) != 0) {
                return false;
            } else {
                check  |= mask;
            }
        }
        return true;
    }
}