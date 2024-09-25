package com.bottomlord.week_125;

/**
 * @author chen yue
 * @date 2021-12-04 14:27:39
 */
public class LeetCode_1903_1_字符串中的最大奇数 {
    public String largestOddNumber(String num) {
        int n = num.length();

        boolean flag = false;
        String max = "0";
        for (int i = n; i >= 1; i--) {
            for (int j = 0; j + i <= n; j++) {
                if (num.charAt(j) == '0') {
                    continue;
                }

                if (Integer.parseInt(num.charAt(j + i - 1) + "") % 2 == 1) {
                    flag = true;
                    max = max(num.substring(j, j + i), max);
                }
            }

            if (flag) {
                return max;
            }
        }

        return "";
    }

    private String max(String x, String y) {
        int num = x.compareTo(y);
        return num == 0 ? x : num > 0 ? x : y;
    }
}
