package com.bottomlord.week_007;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_830_1_较大分组的位置 {
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> ans = new ArrayList<>();
        if (S.length() <= 1) {
            return ans;
        }

        char[] cs = S.toCharArray();
        int start = 0;
        for (int i = 1; i < cs.length; i++) {
            if (cs[i] != cs[i - 1]) {
                if (i - 1 - start >= 2) {
                    List<Integer> list = new ArrayList<>();
                    list.add(start);
                    list.add(i - 1);
                    ans.add(list);
                }
                start = i;
            }
        }

        if (start == 0) {
            List<Integer> list = new ArrayList<>();
            list.add(start);
            list.add(cs.length - 1);
            ans.add(list);
        }

        return ans;
    }
}
