package com.bottomlord.week_105;

public class LeetCode_1323_1_6和9组成的最大数字 {
    public int maximum69Number (int num) {
        String str = Integer.toString(num);
        char[] cs = str.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '6') {
                cs[i] = '9';
                break;
            }
        }

        return Integer.parseInt(new String(cs));
    }
}
