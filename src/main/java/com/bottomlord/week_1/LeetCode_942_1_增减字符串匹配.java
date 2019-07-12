package com.bottomlord.week_1;

/**
 * @author LiveForExperience
 * @date 2019/7/12 12:59
 */
public class LeetCode_942_1_增减字符串匹配 {
    public int[] diStringMatch(String S) {
        int left = 0, right = S.length(), i = 0;
        int[] ans = new int[S.length() + 1];

        for (char c: S.toCharArray()) {
            if (c == 'D') {
                ans[i++] = right--;
            }

            if (c == 'I') {
                ans[i++] = left++;
            }
        }

        ans[i] = left;

        return ans;
    }
}
