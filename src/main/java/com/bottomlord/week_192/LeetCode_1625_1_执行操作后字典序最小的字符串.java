package com.bottomlord.week_192;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @author chen yue
 * @date 2023-03-19 00:02:33
 */
public class LeetCode_1625_1_执行操作后字典序最小的字符串 {
    public String findLexSmallestString(String s, int a, int b) {
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(s);
        String ans = s;
        Set<String> set = new HashSet<>();
        set.add(ans);
        int n = s.length();
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            if (cur.compareTo(ans) < 0) {
                ans = cur;
            }

            char[] cs = cur.toCharArray();
            for (int i = 1; i < n; i+=2) {
                cs[i] = (char)(((cs[i] - '0') + a) % 10 + '0');
            }

            String s1 = String.valueOf(cs);
            String s2 = cur.substring(cur.length() - b) + cur.substring(0, cur.length() - b);

            if (set.add(s1)) {
                queue.offer(s1);
            }

            if (set.add(s2)) {
                queue.offer(s2);
            }
        }

        return ans;
    }
}
