package com.bottomlord.week_3;

import java.util.Objects;

/**
 * @author LiveForExperience
 * @date 2019/7/28 11:25
 */
public class LeetCode_13_2 {
    public int romanToInt(String s) {
        int ans = 0;
        int pre = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == 'I') {
                if (pre > 1) {
                    ans -= 1;
                } else {
                    ans += 1;
                }
                pre = 1;
            } else if (s.charAt(i) == 'V') {
                ans += 5;
                pre = 5;
            } else if (s.charAt(i) == 'X') {
                if (pre > 10) {
                    ans -= 10;
                } else {
                    ans += 10;
                }
                pre = 10;
            } else if (s.charAt(i) == 'L') {
                ans += 50;
                pre = 50;
            } else if (s.charAt(i) == 'C') {
                if (pre > 100) {
                    ans -= 100;
                } else {
                    ans += 100;
                }
                pre = 100;
            } else if (s.charAt(i) == 'D') {
                ans += 500;
                pre = 500;
            } else if (s.charAt(i) == 'M'){
                ans += 1000;
                pre = 1000;
            }
        }
        return ans;
    }
}