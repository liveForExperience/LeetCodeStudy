package com.bottomlord.week_007;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_1018_2 {
    public List<Boolean> prefixesDivBy5(int[] A) {
        int pre = 0;
        List<Boolean> ans = new ArrayList<>(A.length);
        for (int i = 0; i < A.length; i++) {
            pre = ((pre << 1) + A[i]) % 5;
            ans.add(pre == 0);
        }

        return ans;
    }
}