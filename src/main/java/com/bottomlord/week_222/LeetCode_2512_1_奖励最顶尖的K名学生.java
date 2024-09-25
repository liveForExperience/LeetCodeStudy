package com.bottomlord.week_222;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-10-11 09:29:52
 */
public class LeetCode_2512_1_奖励最顶尖的K名学生 {
    public List<Integer> topStudents(String[] positives, String[] negatives, String[] reports, int[] ids, int k) {
        Set<String> pset = new HashSet<>(), nset = new HashSet<>();
        Collections.addAll(pset, positives);
        Collections.addAll(nset, negatives);
        int n = reports.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> {
            if (x[0] == y[0]) {
                return y[1] - x[1];
            }

            return x[0] - y[0];
        });
        for (int i = 0; i < n; i++) {
            String report = reports[i];
            String[] words = report.split(" ");
            int score = 0;
            for (String word : words) {
                if (pset.contains(word)) {
                    score += 3;
                }

                if (nset.contains(word)) {
                    score--;
                }
            }

            queue.offer(new int[]{score, ids[i]});

            if (queue.size() > k) {
                queue.poll();
            }
        }

        List<Integer> ans = new ArrayList<>();
        k = queue.size();
        for (int i = 0; i < k; i++) {
            ans.add(null);
        }
        while (!queue.isEmpty()) {
            ans.set(--k, queue.poll()[1]);
        }
        return ans;
    }
}
