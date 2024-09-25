package com.bottomlord.week_186;

import java.util.*;

/**
 * @author chen yue
 * @date 2023-02-02 20:06:53
 */
public class LeetCode_1129_1_颜色交替的最短路径 {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        Map<Integer, List<Integer>> redMap = new HashMap<>(), blueMap = new HashMap<>();

        int[] ans = new int[n];
        Arrays.fill(ans, Integer.MAX_VALUE);

        for (int[] redEdge : redEdges) {
            redMap.computeIfAbsent(redEdge[0], x -> new ArrayList<>()).add(redEdge[1]);
        }

        for (int[] blueEdge : blueEdges) {
            blueMap.computeIfAbsent(blueEdge[0], x -> new ArrayList<>()).add(blueEdge[1]);
        }

        ans[0] = 0;
        if (redMap.containsKey(0)) {
            boolean[] blueMemo = new boolean[n], redMemo = new boolean[n];
            int path = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            List<Integer> list = redMap.get(0);
            for (Integer edge : list) {
                queue.offer(edge);
            }

            while (!queue.isEmpty()) {
                path++;
                boolean flag = path % 2 == 1;
                int count = queue.size();
                while (count-- > 0) {
                    Integer num = queue.poll();
                    if (num == null) {
                        continue;
                    }

                    if (flag) {
                        if (blueMemo[num]) {
                            continue;
                        } else {
                            blueMemo[num] = true;
                        }
                    } else {
                        if (redMemo[num]) {
                            continue;
                        } else {
                            redMemo[num] = true;
                        }
                    }

                    ans[num] = Math.min(ans[num], path);

                    List<Integer> outDegrees = path % 2 == 1 ? blueMap.getOrDefault(num, new ArrayList<>()) : redMap.getOrDefault(num, new ArrayList<>());
                    for (Integer outDegree : outDegrees) {
                        queue.offer(outDegree);
                    }
                }
            }
        }

        if (blueMap.containsKey(0)) {
            boolean[] blueMemo = new boolean[n], redMemo = new boolean[n];
            int path = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            List<Integer> list = blueMap.get(0);
            for (Integer edge : list) {
                queue.offer(edge);
            }

            while (!queue.isEmpty()) {
                path++;
                boolean flag = path % 2 == 1;
                int count = queue.size();
                while (count-- > 0) {
                    Integer num = queue.poll();
                    if (num == null) {
                        continue;
                    }

                    if (flag) {
                        if (redMemo[num]) {
                            continue;
                        } else {
                            redMemo[num] = true;
                        }
                    } else {
                        if (blueMemo[num]) {
                            continue;
                        } else {
                            blueMemo[num] = true;
                        }
                    }

                    ans[num] = Math.min(ans[num], path);

                    List<Integer> outDegrees = path % 2 == 1 ? redMap.getOrDefault(num, new ArrayList<>()) : blueMap.getOrDefault(num, new ArrayList<>());
                    for (Integer outDegree : outDegrees) {
                        queue.offer(outDegree);
                    }
                }
            }
        }

        for (int i = 0; i < ans.length; i++) {
            if (ans[i] == Integer.MAX_VALUE) {
                ans[i] = -1;
            }
        }

        return ans;
    }
}
