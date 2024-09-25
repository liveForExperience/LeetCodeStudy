package com.bottomlord.week_112;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-08-30 09:13:52
 */
public class LeetCode_1560_2 {
    public List<Integer> mostVisited(int n, int[] rounds) {
        int left = rounds[0], right = rounds[rounds.length - 1];

        List<Integer> ans = new ArrayList<>();
        if (left <= right) {
            for (int i = left; i <= right; i++) {
                ans.add(i);
            }
            return ans;
        }

        for (int i = left; i <= n; i++) {
            ans.add(i);
        }

        for (int i = 1; i <= right; i++) {
            ans.add(i);
        }

        return ans;
    }
}
