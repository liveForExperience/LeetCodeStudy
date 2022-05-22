package com.bottomlord.week_149;

/**
 * @author chen yue
 * @date 2022-05-22 20:43:36
 */
public class LeetCode_2269_1_找到一个数字的K美丽值 {
    public int divisorSubstrings(int num, int k) {
        String str = String.valueOf(num);
        int n = str.length(), count = 0;
        for (int i = 0; i + k <= n; i++) {
            int cur = Integer.parseInt(str.substring(i, i + k));
            if (cur != 0 && num % cur == 0) {
                count++;
            }
        }

        return count;
    }
}
