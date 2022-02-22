package com.bottomlord.week_137;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-02-22 09:28:16
 */
public class LeetCode_838_2 {
    public String pushDominoes(String dominoes) {
        int len = dominoes.length();
        List<Character>[] forces = new ArrayList[len];
        int[] times = new int[len];
        Arrays.fill(times, -1);
        for (int i = 0; i < len; i++) {
            forces[i] = new ArrayList<>();
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            char force = dominoes.charAt(i);
            if (force != '.') {
                queue.offer(i);
                times[i] = 0;
                forces[i].add(force);
            }
        }

        char[] cs = new char[len];
        Arrays.fill(cs, '.');
        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (forces[index].size() == 1) {
                char force = forces[index].get(0);
                cs[index] = force;
                int nextIndex = force == 'L' ? index - 1 : index + 1;
                if (nextIndex >= 0 && nextIndex < len) {
                    int time = times[index];
                    if (times[nextIndex] == -1) {
                        queue.offer(nextIndex);
                        times[nextIndex] = time + 1;
                        forces[nextIndex].add(force);
                    } else if (times[nextIndex] == time + 1) {
                        forces[nextIndex].add(force);
                    }
                }
            }
        }

        return new String(cs);
    }
}
