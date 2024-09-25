package com.bottomlord.week_187;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-02-08 08:25:03
 */
public class LeetCode_1233_1_删除子文件夹 {
    public List<String> removeSubfolders(String[] folder) {
        int n = folder.length;
        boolean[] memo = new boolean[n];
        for (int i = 0; i < folder.length; i++) {
            String str = folder[i];
            for (int j = 0; j < folder.length; j++) {
                if (i == j) {
                    continue;
                }

                if (folder[j].startsWith(str + "/")) {
                    memo[j] = true;
                }
            }
        }

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < memo.length; i++) {
            if (!memo[i]) {
                ans.add(folder[i]);
            }
        }
        return ans;
    }
}
