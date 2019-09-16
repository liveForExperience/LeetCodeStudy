package com.bottomlord.week_011;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_763_1_划分字母区间 {
    public List<Integer> partitionLabels(String S) {
        List<Integer> ans = new ArrayList<>();
        char[] cs = S.toCharArray();
        int start = 0, end = 0;
        for (int i = 0; i < cs.length; i++) {
            int next = S.indexOf(cs[i], i + 1);
            if (next == -1 && i >= end) {
                ans.add(i - start + 1);
                start = i + 1;
            } else if (next > end) {
                end = next;
            }
        }
        return ans;
    }
}