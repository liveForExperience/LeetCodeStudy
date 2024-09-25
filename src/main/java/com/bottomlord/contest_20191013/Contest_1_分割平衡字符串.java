package com.bottomlord.contest_20191013;

public class Contest_1_分割平衡字符串 {
    public int balancedStringSplit(String s) {
        int l = 0, r = 0, count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'L') {
                l++;
            } else {
                r++;
            }

            if (l == r && l != 0) {
                count++;
                l = 0;
                r = 0;
            }
        }

        return count;
    }
}
