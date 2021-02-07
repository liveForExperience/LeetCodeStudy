package com.bottomlord.week_082;

import java.util.*;

/**
 * @author ChenYue
 * @date 2021/2/7 16:36
 */
public class LeetCode_444_1_序列重建 {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        int n = org.length;
        if (n == 0 || seqs.isEmpty()) {
            return false;
        }

        Set<Integer> set = new HashSet<>();
        for (List<Integer> list : seqs) {
            for (int num : list) {
                if (num <= 0 || num > n) {
                    return false;
                }

                set.add(num);
            }
        }

        if (set.size() < n) {
            return false;
        }

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }


        for (List<Integer> seq : seqs) {
            for (int i = 0; i < seq.size() - 1; i++) {
                adj.get(seq.get(i)).add(seq.get(i + 1));
            }
        }

        int[] inDegree = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < adj.get(i).size(); j++) {
                inDegree[adj.get(i).get(j)]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        if (queue.size() != 1) {
            return false;
        }

        int index = 0;
        while (!queue.isEmpty()) {
            Integer num = queue.poll();
            if (num == null) {
                continue;
            }

            if (org[index++] != num) {
                return false;
            }

            boolean hasZero = false;
            for (int in : adj.get(num)) {
                inDegree[in]--;
                if (inDegree[in] == 0) {
                    if (hasZero) {
                        return false;
                    }

                    hasZero = true;
                    queue.offer(in);
                }
            }
        }

        return index == n;
    }
}
