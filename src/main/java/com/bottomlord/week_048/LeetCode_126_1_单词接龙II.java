package com.bottomlord.week_048;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/6/7 12:06
 */
public class LeetCode_126_1_单词接龙II {
    private Map<String, Integer> map = new HashMap<>();
    private List<String> list = new ArrayList<>();
    private List<Integer>[] edges;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        int index = 0;
        for (String word : wordList) {
            map.put(word, index++);
            list.add(word);
        }

        if (!map.containsKey(endWord)) {
            return Collections.emptyList();
        }

        if (!map.containsKey(beginWord)) {
            map.put(beginWord, index);
            list.add(beginWord);
        }

        edges = new ArrayList[list.size()];
        for (int i = 0; i < list.size(); i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (hasOneDiff(list.get(i), list.get(j))) {
                    edges[i].add(j);
                    edges[j].add(i);
                }
            }
        }

        int[] cost = new int[list.size()];
        Arrays.fill(cost, 1 << 20);

        Queue<List<Integer>> queue = new ArrayDeque<>();
        cost[map.get(beginWord)] = 0;
        int dest = map.get(endWord);
        List<Integer> beginList = new ArrayList<>();
        beginList.add(map.get(beginWord));
        queue.add(beginList);
        List<List<String>> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            List<Integer> now = queue.poll();
            Integer curr = now.get(now.size() - 1);

            if (curr == dest) {
                List<String> tmp = new ArrayList<>();
                for (int i : now) {
                    tmp.add(this.list.get(i));
                }
                ans.add(tmp);
            } else {
                List<Integer> toList = edges[curr];

                for (int to : toList) {
                    if (cost[curr] + 1 <= cost[to]) {
                        cost[to] = cost[curr] + 1;
                        List<Integer> tmp = new ArrayList<>(now);
                        tmp.add(to);
                        queue.add(tmp);
                    }
                }
            }
        }

        return ans;
    }

    private boolean hasOneDiff(String one, String two) {
        int diff = 0;
        for (int i = 0; i < one.length() && diff < 2; i++) {
            if (one.charAt(i) != two.charAt(i)) {
                diff++;
            }
        }

        return diff == 1;
    }
}
