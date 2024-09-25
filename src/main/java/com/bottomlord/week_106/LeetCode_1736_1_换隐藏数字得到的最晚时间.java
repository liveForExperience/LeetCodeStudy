package com.bottomlord.week_106;

public class LeetCode_1736_1_换隐藏数字得到的最晚时间 {
    public String maximumTime(String time) {
        char[] cs = time.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] != '?') {
                continue;
            }

            if (i == 0) {
                if (cs[1] == '?') {
                    cs[0] = '2';
                    cs[1] = '3';
                    i++;
                } else if (cs[1] >= '0' + 4) {
                    cs[0] = '1';
                    i++;
                } else {
                    cs[0] = '2';
                    i++;
                }
                continue;
            }

            if (i == 1) {
                if (cs[0] == '0' || cs[0] == '1') {
                    cs[1] = '9';
                } else {
                    cs[1] = '3';
                }
                continue;
            }

            if (i == 3) {
                cs[3] = '5';
            } else {
                cs[4] = '9';
            }
        }

        return new String(cs);
    }
}
