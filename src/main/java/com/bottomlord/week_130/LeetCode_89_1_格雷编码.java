package com.bottomlord.week_130;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-01-09 21:03:50
 */
public class LeetCode_89_1_格雷编码 {
    public List<Integer> grayCode(int n) {
        int head = 1;
        List<Integer> ans = new ArrayList<>();
        ans.add(0);

        for (int i = 0; i < n; i++) {
            for (int j = ans.size() - 1; j >= 0; j--) {
                ans.add(head + ans.get(j));
            }
            head <<= 1;
        }

        return ans;
    }
}
