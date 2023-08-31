package com.bottomlord.week_216;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author chen yue
 * @date 2023-08-30 18:45:17
 */
public class LeetCode_1654_1_到家的最少跳跃次数 {
    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        int max = Math.max(Arrays.stream(forbidden).max().getAsInt() + a, x) + b;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        int ans = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : forbidden) {
            set.add(num);
        }

        boolean[][] memo = new boolean[max + 1][2];
        memo[0][0] = true;
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            while (cnt-- > 0) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                int cur = arr[0], dir = arr[1], pre = cur - b, next = cur + a;

                if (cur == x) {
                    return ans;
                }

                if (dir == 0 && pre >= 0 && !set.contains(pre) && !memo[pre][1]) {
                    memo[pre][1] = true;
                    queue.offer(new int[]{pre, 1});
                }

                if (next <= max && !set.contains(next) && !memo[next][0]) {
                    memo[next][0] = true;
                    queue.offer(new int[]{next, 0});
                }
            }
            ans++;
        }

        return -1;
    }
}
