package com.bottomlord.week_063;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/9/14 8:21
 */
public class LeetCode_269_1_火星词典 {
    public String alienOrder(String[] words) {
        if (words.length == 0) {
            return "";
        }

        Map<Character, Set<Character>> map = new HashMap<>();
        for (int i = 0; i < words.length - 1; i++) {
            int len = Math.max(words[i].length(), words[i + 1].length());

            for (int j = 0; j < len; j++) {
                if (j >= words[i].length()) {
                    break;
                }

                if (j >= words[i + 1].length()) {
                    return "";
                }

                if (words[i].charAt(j) == words[i + 1].charAt(j)) {
                    continue;
                }

                Set<Character> set = map.getOrDefault(words[i].charAt(j), new HashSet<>());
                set.add(words[i + 1].charAt(j));
                map.put(words[i].charAt(j), set);
                break;
            }
        }

        int[] degree = new int[26];
        Arrays.fill(degree, -1);
        for (String word : words) {
            for (char c : word.toCharArray()) {
                degree[c - 'a'] = 0;
            }
        }

        for (Character key : map.keySet()) {
            for (Character c : map.get(key)) {
                degree[c - 'a']++;
            }
        }

        Queue<Character> queue = new ArrayDeque<>();
        int count = 0;
        for (int i = 0; i < degree.length; i++) {
            if (degree[i] != -1) {
                count++;
            }

            if (degree[i] == 0) {
                queue.offer((char) (i + 'a'));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character key = queue.poll();
            sb.append(key);

            if (map.containsKey(key)) {
                Set<Character> set = map.get(key);
                for (char c : set) {
                    degree[c - 'a']--;
                    if (degree[c - 'a'] == 0) {
                        queue.offer(c);
                    }
                }
            }
        }

        return sb.length() == count ? sb.toString() : "";
    }
}
