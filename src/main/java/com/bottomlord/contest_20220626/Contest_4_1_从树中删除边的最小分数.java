package com.bottomlord.contest_20220626;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chen yue
 * @date 2022-06-25 23:26:51
 */
public class Contest_4_1_从树中删除边的最小分数 {
    private int clock;
    private int[] in, out, xor, nums;
    private List<Integer>[] g;
    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        this.nums = nums;
        in = new int[n];
        out = new int[n];
        xor = new int[n];
        g = new ArrayList<>[n];

        Arrays.setAll(g, x -> new ArrayList<Integer>());
        for (int[] edge : edges) {
            int x = edge[0], y = edge[1];
            g[x].add(y);
            g[y].add(x);
        }

        dfs(0, -1);
        int ans = Integer.MAX_VALUE;
        for (int x = 1; x < n; x++) {
            for (int y = x + 1; y < n; y++) {
                int a, b, c;
                if (in[x] < in[y] && in[y] <= out[x]) {
                    a = xor[y];
                    b = xor[x] ^ a;
                    c = xor[0] ^ xor[x];
                } else if (in[y] < in[x] && in[x] <= out[y]) {
                    a = xor[x];
                    b = xor[y] ^ a;
                    c = xor[0] ^ xor[y];
                } else {
                    a = xor[x];
                    b = xor[y];
                    c = xor[0] ^ a ^ b;
                }

                int cur = Math.max(a, Math.max(b, c)) - Math.min(a, Math.min(b, c));
                ans = Math.min(cur, ans);

                if (ans == 0) {
                    return 0;
                }
            }
        }

        return ans;
    }

    private void dfs(int x, int pre) {
        in[x] = clock++;
        List<Integer> next = g[x];
        xor[x] = nums[x];
        for (Integer y : next) {
            if (y != pre) {
                dfs(y, x);
                xor[x] ^= xor[y];
            }
        }
        out[x] = clock++;
    }
}
