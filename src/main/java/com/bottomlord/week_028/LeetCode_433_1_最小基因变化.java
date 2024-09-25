package com.bottomlord.week_028;

import java.util.*;

/**
 * @author ThinkPad
 * @date 2020/1/13 20:49
 */
public class LeetCode_433_1_最小基因变化 {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        int level = 0;

        queue.offer(start);
        bankSet.add(start);

        char[] genes = new char[]{'A', 'T', 'G', 'C'};

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                String str = queue.poll();
                if (str == null) {
                    continue;
                }

                if (Objects.equals(str, end)) {
                    return level;
                }

                for (int i = 0; i < str.length(); i++) {
                    char[] cs = str.toCharArray();
                    for (int j = 0; j < 4; j++) {
                        if (cs[i] != genes[j]) {
                            cs[i] = genes[j];
                        }

                        String tmp = new String(cs);

                        if (!set.contains(tmp) && bankSet.contains(tmp)) {
                            set.add(tmp);
                            queue.offer(tmp);
                        }
                    }
                }
            }

            level++;
        }

        return -1;
    }
}