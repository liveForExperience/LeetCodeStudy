package com.bottomlord.week_134;

import java.util.Arrays;

/**
 * @author chen yue
 * @date 2022-02-01 11:13:12
 */
public class LeetCode_LCS02_1_完成一半题目 {
    public int halfQuestions(int[] questions) {
        int[] bucket = new int[1001];
        for (int question : questions) {
            bucket[question]++;
        }

        Arrays.sort(bucket);
        int n = questions.length / 2, ans = 0;

        for (int i = bucket.length - 1; i >= 0; i--) {
            if (bucket[i] == 0) {
                continue;
            }

            n -= bucket[i];
            ans++;
            if (n <= 0) {
                break;
            }
        }

        return ans;
    }
}
