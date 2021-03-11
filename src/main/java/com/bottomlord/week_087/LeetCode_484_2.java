package com.bottomlord.week_087;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2021/3/11 8:51
 */
public class LeetCode_484_2 {
    public int[] findPermutation(String s) {
        int[] ans = new int[s.length() + 1];
        for (int i = 1; i <= s.length() + 1; i++) {
            ans[i - 1] = i;
        }

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'I') {
                continue;
            }

            int j = i;
            while (i < s.length() && s.charAt(i) == 'D') {
                i++;
            }

            reserve(ans, j, i);
        }

        return ans;
    }

    private void reserve(int[] arr, int start, int end) {
        while (start <= end) {
            int tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            start++;
            end--;
        }
    }
}
