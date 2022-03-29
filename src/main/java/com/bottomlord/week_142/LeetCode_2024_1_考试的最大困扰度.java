package com.bottomlord.week_142;

/**
 * @author chen yue
 * @date 2022-03-29 19:26:21
 */
public class LeetCode_2024_1_考试的最大困扰度 {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(count(answerKey, 'T', k), count(answerKey, 'F', k));
    }

    private int count(String key, char target, int k) {
        int ans = 0, left = 0, n = key.length(), cur = 0;
        for (int right = 0; right < n; right++) {
            cur += key.charAt(right) != target ? 1 : 0;

            while (cur > k) {
                cur -= key.charAt(left++) != target ? 1 : 0;
            }

            ans = Math.max(right - left + 1, ans);
        }

        return ans;
    }
}
