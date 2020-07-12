package com.bottomlord.week_053;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/7/10 8:44
 */
public class LeetCode_127_2 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int count = 1;
        Map<String, List<String>> map = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String label = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> list = map.getOrDefault(label, new ArrayList<>());
                list.add(word);
                map.put(label, list);
            }
        }

        Queue<String> queue = new ArrayDeque<>();
        Set<String> memo = new HashSet<>();
        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String word = queue.poll();
                if (word == null) {
                    continue;
                }

                if (memo.contains(word)) {
                    continue;
                }

                memo.add(word);

                if (Objects.equals(endWord, word)) {
                    return count;
                }

                for (int i = 0; i < word.length(); i++) {
                    String label = word.substring(0, i) + "*" + word.substring(i + 1);
                    for (String match : map.getOrDefault(label, new ArrayList<>())) {
                        queue.offer(match);
                    }
                }
            }

            count++;
        }

        return 0;
    }
}