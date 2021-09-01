package com.bottomlord.week_112;

/**
 * @author chen yue
 * @date 2021-09-01 08:23:10
 */
public class LeetCode_165_1_比较版本号 {
    public int compareVersion(String version1, String version2) {
        String[] vs1 = version1.split("\\."), vs2 = version2.split("\\.");
        int len = Math.max(vs1.length, vs2.length);

        for (int i = 0; i < len; i++) {
            int num1 = i >= vs1.length ? 0 : Integer.parseInt(vs1[i]),
                num2 = i >= vs2.length ? 0 : Integer.parseInt(vs2[i]);

            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }
        }

        return 0;
    }
}
