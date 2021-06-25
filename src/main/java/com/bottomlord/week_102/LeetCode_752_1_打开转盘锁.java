package com.bottomlord.week_102;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ChenYue
 * @date 2021/6/25 8:33
 */
public class LeetCode_752_1_打开转盘锁 {
    public int openLock(String[] deadends, String target) {
        if (Objects.equals("0000", target)) {
            return 0;
        }

        int step = 0;
        Set<String> memo = new HashSet<>(), dead = Arrays.stream(deadends).collect(Collectors.toSet());
        if (dead.contains("0000")) {
            return -1;
        }

        Queue<String> queue = new ArrayDeque<>();
        queue.offer("0000");

        while (!queue.isEmpty()) {
            int size = queue.size();

            step++;
            for (int i = 0; i < size; i++) {
                String status = queue.poll();
                for (String newStatus : get(status)) {
                    if (Objects.equals(newStatus, target)) {
                        return step;
                    }

                    if (memo.contains(newStatus) || dead.contains(newStatus)) {
                        continue;
                    }

                    queue.offer(newStatus);
                    memo.add(newStatus);
                }
            }
        }

        return -1;
    }

    private List<String> get(String status) {
        List<String> list = new ArrayList<>(8);
        for (int i = 0; i < 4; i++) {
            add(list, status, i, this::getPre);
            add(list, status, i, this::getNext);
        }
        return list;
    }

    private char getPre(char c) {
        return c == '0' ? '9' : (char)(c - 1);
    }

    private char getNext(char c) {
        return c == '9' ? '0' : (char)(c + 1);
    }

    private void add(List<String> list, String status, int index, Function<Character, Character> function) {
        char[] cs = status.toCharArray();
        cs[index] = function.apply(cs[index]);
        list.add(new String(cs));
    }
}
