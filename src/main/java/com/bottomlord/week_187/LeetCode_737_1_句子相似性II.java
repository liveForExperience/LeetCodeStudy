package com.bottomlord.week_187;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-02-11 17:53:00
 */
public class LeetCode_737_1_句子相似性II {

    private int index = 0;
    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        if (sentence1.length != sentence2.length) {
            return false;
        }

        Map<String, Integer> map = new HashMap<>();
        int index = 0;
        for (String str : sentence1) {
            generate(str, map);
        }

        for (String str : sentence2) {
            generate(str, map);
        }

        for (List<String> similarPair : similarPairs) {
            for (String str : similarPair) {
                generate(str, map);
            }
        }

        Uf uf = new Uf(index);
        for (List<String> similarPair : similarPairs) {
            for (int i = 1; i < similarPair.size(); i++) {
                uf.union(map.get(similarPair.get(i)), map.get(similarPair.get(i - 1)));
            }
        }

        for (int i = 0; i < sentence1.length; i++) {
            if (uf.find(map.get(sentence1[i])) != uf.find(map.get(sentence2[i]))) {
                return false;
            }
        }

        return true;
    }

    private void generate(String str, Map<String, Integer> map) {
        if (map.containsKey(str)) {
            return;
        }

        map.put(str, index++);
    }

    private class Uf {
        private int[] parent;

        public Uf(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int x, int y) {
            int rx = find(x), ry = find(y);
            parent[rx] = ry;
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }
    }
}
