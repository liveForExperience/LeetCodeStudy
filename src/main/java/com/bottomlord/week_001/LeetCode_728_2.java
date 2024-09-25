package com.bottomlord.week_001;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiveForExperience
 * @date 2019/7/13 16:38
 */
public class LeetCode_728_2 {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            int t = i;
            boolean flag = true;
            while (t != 0) {
                int s = t % 10;
                if (s == 0 || i % s != 0) {
                    flag = false;
                    break;
                }

                t /= 10;
            }

            if (flag) {
                ans.add(i);
            }
        }

        return ans;
    }
}
