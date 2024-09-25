package com.bottomlord.week_057;

/**
 * @author ChenYue
 * @date 2020/8/5 8:17
 */
public class LeetCode_165_1_比较版本号 {
    public int compareVersion(String version1, String version2) {
        String[] v1s = version1.split("\\."), v2s = version2.split("\\.");
        int len1 = v1s.length, len2 = v2s.length, index = 0;

        while (index < len1 || index < len2) {
            int v1Num = index >= len1 ? 0 : Integer.parseInt(v1s[index]),
                v2Num = index >= len2 ? 0 : Integer.parseInt(v2s[index]);

            if (v1Num > v2Num) {
                return 1;
            } else if (v1Num < v2Num) {
                return -1;
            }

            index++;
        }

        return 0;
    }
}
