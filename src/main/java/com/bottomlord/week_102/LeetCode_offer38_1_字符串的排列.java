package com.bottomlord.week_102;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2021/6/22 8:17
 */
public class LeetCode_offer38_1_字符串的排列 {
    public String[] permutation(String s) {
        Set<String> ans = new HashSet<>();
        backTrack(s, new StringBuilder(), new HashSet<>(), ans);
        return ans.toArray(new String[0]);
    }

    private void backTrack(String s, StringBuilder sb, Set<Integer> memo, Set<String> ans) {
        if (memo.size() == s.length()) {
            ans.add(sb.toString());
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            if (memo.contains(i)) {
                continue;
            }

            int len = sb.length();
            sb.append(s.charAt(i));
            memo.add(i);
            backTrack(s, sb, memo, ans);
            memo.remove(i);
            sb.setLength(len);
        }
    }
}
