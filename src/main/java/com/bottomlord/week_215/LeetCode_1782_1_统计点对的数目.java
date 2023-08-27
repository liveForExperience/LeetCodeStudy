package com.bottomlord.week_215;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2023-08-26 14:19:07
 */
public class LeetCode_1782_1_统计点对的数目 {
    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int[] degrees = new int[n + 1];
        Map<Integer, Integer> edgeMap = new HashMap<>();
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            if (x > y) {
                int tmp = y;
                y = x;
                x = tmp;
            }

            degrees[x]++;
            degrees[y]++;
            int mask = (x << 16) | y;
            edgeMap.put(mask, edgeMap.getOrDefault(mask, 0) + 1);
        }

        int[] ans = new int[queries.length];
        int[] sortedDegrees = degrees.clone();
        Arrays.sort(sortedDegrees);
        for (int i = 0; i < queries.length; i++) {
            int a = 1, b = n, query = queries[i];
            while (a < b) {
                if (sortedDegrees[a] + sortedDegrees[b] <= query) {
                    a++;
                } else {
                    ans[i] += b - a;
                    b--;
                }
            }

            for (Map.Entry<Integer, Integer> entry : edgeMap.entrySet()) {
                int mask = entry.getKey(), c = entry.getValue(),
                        x = mask >> 16, y = mask & 0xffff, sum = degrees[x] + degrees[y];

                if (sum > query && sum - c <= query) {
                    ans[i]--;
                }
            }
        }

        return ans;
    }
}
