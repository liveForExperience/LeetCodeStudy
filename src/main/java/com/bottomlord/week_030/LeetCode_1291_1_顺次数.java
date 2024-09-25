package com.bottomlord.week_030;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/1/31 8:41
 */
public class LeetCode_1291_1_顺次数 {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            int num = i;
            for (int j = i + 1; j <= 9; j++) {
                num = num * 10 + j;
                if (num >= low && num <= high) {
                    ans.add(num);
                }
            }
        }

        Collections.sort(ans);
        return ans;
    }
}
