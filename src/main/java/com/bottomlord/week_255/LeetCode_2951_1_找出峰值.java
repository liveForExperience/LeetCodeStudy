package com.bottomlord.week_255;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2024-05-28 22:18:59
 */
public class LeetCode_2951_1_找出峰值 {
    public List<Integer> findPeaks(int[] mountain) {
        int n = mountain.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            if (mountain[i] > mountain[i - 1] && mountain[i] > mountain[i + 1]) {
                ans.add(i);
            }
        }
        return ans;
    }
}
