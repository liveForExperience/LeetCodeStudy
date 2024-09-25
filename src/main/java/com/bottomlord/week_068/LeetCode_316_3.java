package com.bottomlord.week_068;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/10/27 18:35
 */
public class LeetCode_316_3 {
    public String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        Set<Character> set = new HashSet<>();
        Map<Character, Integer> map = new HashMap<>();

        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            map.put(cs[i], i);
        }

        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (!set.contains(c)) {
                while (!stack.isEmpty() && c < stack.peek() && map.get(stack.peek()) > i) {
                    set.remove(stack.pop());
                }
            }
            set.add(c);
            stack.push(c);
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
