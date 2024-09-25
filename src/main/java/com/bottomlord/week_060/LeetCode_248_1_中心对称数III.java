package com.bottomlord.week_060;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * @author ChenYue
 * @date 2020/8/29 11:45
 */
public class LeetCode_248_1_中心对称数III {
    public int strobogrammaticInRange(String low, String high) {
        char[][] cs = new char[][]{{'0','0'},{'1','1'},{'6','9'},{'8','8'},{'9','6'}};
        Queue<String> queue = new ArrayDeque<>();
        queue.offer("");
        queue.offer("0");
        queue.offer("1");
        queue.offer("8");

        int ans = 0;
        while (!queue.isEmpty()) {
            String s = queue.poll();
            if (s.length() >= low.length() && s.length() <= high.length()) {
                if (!(s.length() > 1 && s.charAt(0) == '0')) {
                    if (check(s, low, high)) {
                        ans++;
                    }
                }
            }

            if (s.length() > high.length()) {
                continue;
            }

            for (char[] c : cs) {
                String next = c[0] + s + c[1];
                if (next.length() <= high.length()) {
                    queue.offer(next);
                }
            }
        }

        return ans;
    }

    private boolean check(String num, String low, String high) {
        return compare(num, low) && compare(high, num);
    }

    private boolean compare(String a, String b) {
        if (a.length() != b.length()) {
            return a.length() > b.length();
        }

        int r = a.compareTo(b);
        return r > 0 || r == 0;
    }
}
