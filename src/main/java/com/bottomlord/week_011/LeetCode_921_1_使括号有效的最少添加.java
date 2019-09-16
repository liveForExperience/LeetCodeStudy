package com.bottomlord.week_011;

public class LeetCode_921_1_使括号有效的最少添加 {
    public int minAddToMakeValid(String S) {
        int rightH = 0, left = 0;
        char[] cs = S.toCharArray();
        for(int i = 0; i < cs.length; i++) {
            if (cs[i] == '(') {
                left++;
            } else {
                if (left > 0) {
                    left--;
                } else {
                    rightH++;
                }
            }
        }

        return left + rightH;
    }
}
