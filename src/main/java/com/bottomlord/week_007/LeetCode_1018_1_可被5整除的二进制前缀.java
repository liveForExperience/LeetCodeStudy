package com.bottomlord.week_007;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_1018_1_可被5整除的二进制前缀 {
    public List<Boolean> prefixesDivBy5(int[] A) {
        int num = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            num += Math.pow(2, A.length - 1 - i) * A[i];
        }

        List<Boolean> ans = new ArrayList<>(A.length);
        for (int i = A.length - 1; i >= 0; i--){
            ans.add(0, num % 5 == 0);
            num >>= 1;
        }
        return ans;
    }
}
