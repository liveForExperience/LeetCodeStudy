package com.bottomlord.week_158;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-07-23 21:05:08
 */
public class LeetCode_offerII115_1_重建序列 {
    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int n = nums.length;
        for (int i = 1; i <= n; i++) {
            map.put(i, new HashSet<>());
        }

        int[] inDegreeCounts = new int[n + 1];

        for (int[] sequence : sequences) {
            int size = sequence.length;
            for (int i = 1; i < size; i++) {
                int pre = sequence[i - 1], cur = sequence[i];
                Set<Integer> outDegree = map.get(pre);
                if (outDegree.add(cur)) {
                    inDegreeCounts[cur]++;
                }
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> candidate = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (inDegreeCounts[i] == 0) {
                queue.offer(i);
                candidate.add(i);
            }
        }


        while (!queue.isEmpty()) {
            int size = queue.size();
            if (size > 1) {
                return false;
            }

            int cur = queue.poll();

            Set<Integer> outDegrees = map.get(cur);
            for (Integer next : outDegrees) {
                inDegreeCounts[next]--;
                if (inDegreeCounts[next] == 0) {
                    queue.offer(next);
                    candidate.add(next);
                }
            }
        }

        for (int i = 0; i < candidate.size(); i++) {
            if (candidate.get(i) != nums[i]) {
                return false;
            }
        }

        return true;
    }
}
