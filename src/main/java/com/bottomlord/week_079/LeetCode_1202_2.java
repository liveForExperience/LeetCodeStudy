package com.bottomlord.week_079;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author ChenYue
 * @date 2021/1/11 14:40
 */
public class LeetCode_1202_2 {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        Uf uf = new Uf(n);
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }

        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < uf.parents.length; i++) {
            map.computeIfAbsent(uf.find(i), x -> new PriorityQueue<>()).offer(s.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(map.get(uf.find(i)).poll());
        }
        return sb.toString();
    }

    private class Uf {
        int[] parents;
        int[] ranks;
        int count;

        public Uf(int n) {
            count = 0;
            parents = new int[n];
            ranks = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                ranks[i] = 1;
            }
        }

        public void union(int x, int y) {
            int px = find(x), py = find(y);

            if (px == py) {
                return;
            }

            if (ranks[px] < ranks[py]) {
                parents[px] = py;
            } else if (ranks[px] > ranks[py]) {
                parents[py] = px;
            } else {
                parents[py] = px;
                ranks[px]++;
            }
        }

        public int find(int x) {
            if (parents[x] != x) {
                parents[x] = find(parents[x]);
            }

            return parents[x];
        }
    }
}
