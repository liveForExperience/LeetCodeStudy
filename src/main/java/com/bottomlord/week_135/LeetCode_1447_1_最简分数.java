package com.bottomlord.week_135;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-02-10 08:54:53
 */
public class LeetCode_1447_1_最简分数 {
    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (gcd(i, j) == 1) {
                    ans.add(i + "/" + j);
                }
            }
        }
        return ans;
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
