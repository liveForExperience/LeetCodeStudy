package com.bottomlord.week_036;

import java.util.Arrays;

/**
 * @author ThinkPad
 * @date 2020/3/13 8:41
 */
public class Interview_0101_1_判断字符是否唯一 {
    public boolean isUnique(String astr) {
        char[] cs = astr.toCharArray();
        Arrays.sort(cs);

        for (int i = 1; i < cs.length; i++) {
            if (cs[i - 1] == cs[i]) {
                return false;
            }
        }

        return true;
    }
}
