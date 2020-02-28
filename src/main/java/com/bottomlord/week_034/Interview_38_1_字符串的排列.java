package com.bottomlord.week_034;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ThinkPad
 * @date 2020/2/27 13:13
 */
public class Interview_38_1_字符串的排列 {
    public String[] permutation(String s) {
        Set<String> set = new HashSet<>();
        backTrack(new StringBuilder(), s.toCharArray(), set, new boolean[s.length()]);
        return set.toArray(new String[0]);
    }

    private void backTrack(StringBuilder sb, char[] cs, Set<String> set, boolean[] visited) {
        if (sb.length() == cs.length) {
            set.add(sb.toString());
            return;
        }

        for (int i = 0; i < cs.length; i++) {
            if (visited[i]) {
                continue;
            }

            sb.append(cs[i]);
            visited[i] = true;
            backTrack(sb, cs, set, visited);
            visited[i] = false;
            sb.delete(sb.length() - 1, sb.length());
        }
    }
}
