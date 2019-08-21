package com.bottomlord.week_7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeetCode_989_3 {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> ans = new LinkedList<>();
        int index = A.length - 1;
        while (index >= 0 || K > 0) {
            if (index >= 0) {
                K += A[index--];
            }

            ans.add(0, K % 10);
            K /= 10;
        }

        return ans;
    }
}
