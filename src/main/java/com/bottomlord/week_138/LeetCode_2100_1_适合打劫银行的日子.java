package com.bottomlord.week_138;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-03-06 20:28:28
 */
public class LeetCode_2100_1_适合打劫银行的日子 {
    public List<Integer> goodDaysToRobBank(int[] security, int time) {
        int len = security.length;
        int[] left = new int[len], right = new int[len];
        for (int i = 1; i < len; i++) {
            if (security[i] <= security[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }

        for (int i = len - 2; i >= 0; i--) {
            if (security[i] <= security[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (left[i] >= time && right[i] >= time) {
                ans.add(i);
            }
        }

        return ans;
    }
}
