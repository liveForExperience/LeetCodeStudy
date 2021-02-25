package com.bottomlord.week_085;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/2/24 18:13
 */
public class LeetCode_467_1_环绕字符串中唯一的子字符串 {
    public int findSubstringInWraproundString(String p) {
        if (p == null || p.length() == 0) {
            return 0;
        }

        int sum = 1;
        int[] arr = new int[26];
        arr[p.charAt(0) - 'a'] = sum;

        for (int r = 1; r < 26; r++) {
            if (p.charAt(r) - p.charAt(r - 1) == 1 || p.charAt(r) - p.charAt(r - 1) == -25) {
                sum++;
            } else {
                sum = 1;
            }

            arr[p.charAt(r) - 'a'] = Math.max(arr[p.charAt(r) - 'a'], sum);
        }

        return Arrays.stream(arr).sum();
    }
}
