package com.bottomlord.week_193;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-03-23 09:22:34
 */
public class LeetCode_1630_1_等差子数组 {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int n = l.length;
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = l[i], end = r[i];
            int[] sub = Arrays.copyOfRange(nums, start, end + 1);
            Arrays.sort(sub);

            int diff = sub[1] - sub[0];
            boolean flag = true;
            for (int j = 2; j <= end - start; j++) {
                if (sub[j] - sub[j - 1] != diff) {
                    flag = false;
                    break;
                }
            }

            ans.add(flag);
        }
        return ans;
    }
}
