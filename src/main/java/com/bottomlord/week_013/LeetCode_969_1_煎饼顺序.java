package com.bottomlord.week_013;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeetCode_969_1_煎饼顺序 {
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> ans = new ArrayList<>();

        int len = A.length;
        Integer[] b = new Integer[len];
        for (int i = 0; i < len; i++) {
            b[i] = i + 1;
        }
        Arrays.sort(b, (i, j) ->  A[j - 1] - A[i - 1]);

        for (int index : b) {
            for (int preIndex : ans) {
                if (index <= preIndex) {
                    index = preIndex + 1 - index;
                }
            }

            ans.add(index);
            ans.add(len--);
        }

        return ans;
    }
}
