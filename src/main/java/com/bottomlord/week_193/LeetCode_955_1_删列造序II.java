package com.bottomlord.week_193;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chen yue
 * @date 2023-03-23 19:59:26
 */
public class LeetCode_955_1_删列造序II {
    public int minDeletionSize(String[] strs) {
        int n = strs[0].length(), ans = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < strs.length - 1; i++) {
            set.add(i);
        }

        for (int i = 0; i < n; i++) {
            boolean flag = true;
            Set<Integer> curSet = new HashSet<>();
            for (int j = 0; j < strs.length - 1; j++) {
                if (!set.contains(j)) {
                    continue;
                }

                int diff = strs[j].charAt(i) - strs[j + 1].charAt(i);
                if (diff > 0) {
                    flag = false;
                    break;
                } else if (diff == 0) {
                    curSet.add(j);
                }
            }

            if (!flag) {
                ans++;
                continue;
            }

            set = curSet;
            if (set.isEmpty()) {
                break;
            }

        }

        return ans;
    }
}
