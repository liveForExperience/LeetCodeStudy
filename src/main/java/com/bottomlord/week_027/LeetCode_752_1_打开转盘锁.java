package com.bottomlord.week_027;

import java.util.*;

/**
 * @author ThinkPad
 * @date 2020/1/10 15:04
 */
public class LeetCode_752_1_打开转盘锁 {
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>();
        Collections.addAll(dead, deadends);

        Queue<String> queue = new LinkedList<>();
        Set<String> memo = new HashSet<>();
        int depth = 0;
        queue.offer("0000");
        queue.offer(null);

        memo.add("0000");

        while (!queue.isEmpty()) {
            String str = queue.poll();
            if (str == null) {
                depth++;
                if (queue.peek() != null) {
                    queue.offer(null);
                }
            } else if (Objects.equals(target, str)) {
                return depth;
            } else if (!dead.contains(str)){
                for (int i = 0; i < 4; i++) {
                    for (int j = -1; j <= 1; j++) {
                        int num = ((str.charAt(i) - '0') + j + 10) % 10;
                        String tmp = str.substring(0, i) + ("" + num) + str.substring(i + 1);

                        if (!memo.contains(tmp)) {
                            memo.add(tmp);
                            queue.offer(tmp);
                        }
                    }
                }
            }
        }

        return -1;
    }
}
