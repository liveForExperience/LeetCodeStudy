package com.bottomlord.week_112;

/**
 * @author chen yue
 * @date 2021-08-31 08:51:41
 */
public class LeetCode_1566_1_重复至少K次且长度为M的模式 {
    public boolean containsPattern(int[] arr, int m, int k) {
        int n = arr.length;
        for (int i = 0; i + m * k <= n; i++) {
            boolean flag = true;
            for (int j = i + m; j < i + m * k; j++) {
                if (arr[j] != arr[j - m]) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                return true;
            }
        }

        return false;
    }
}
