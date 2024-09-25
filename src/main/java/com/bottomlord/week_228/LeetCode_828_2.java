package com.bottomlord.week_228;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2023-11-26 11:24:48
 */
public class LeetCode_828_2 {
    public int uniqueLetterString(String s) {
        int len = s.length(), ans = 0;
        int[] preArr = new int[26], curArr = new int[26];
        Arrays.fill(preArr, -1);
        Arrays.fill(curArr, -1);

        for (int i = 0; i < len; i++) {
            int ci = s.charAt(i) - 'A';
            if (curArr[ci] > - 1) {
                ans += (i - curArr[ci]) * (curArr[ci] - preArr[ci]);
            }

            preArr[ci] = curArr[ci];
            curArr[ci] = i;
        }

        for (int i = 0; i < curArr.length; i++) {
            if (curArr[i] > -1) {
                ans += (len - curArr[i]) * (curArr[i] - preArr[i]);
            }
        }

        return ans;
    }
}