package com.bottomlord.week_230;

import com.bottomlord.LeetCodeUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author chen yue
 * @date 2023-12-07 17:02:25
 */
public class LeetCode_1466_1_重新规划路线 {
    public int minReorder(int n, int[][] connections) {
        List[] outs = new List[n], ins = new List[n];

        for (int i = 0; i < n; i++) {
            outs[i] = new ArrayList<>();
            ins[i] = new ArrayList();
        }

        for (int[] connection : connections) {
            int from = connection[0], to = connection[1];
            outs[from].add(to);
            ins[to].add(from);
        }

        int ans = 0;
        boolean[] memo = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            int index = queue.poll();
            memo[index] = true;

            List<Integer> outIndexes = outs[index];
            for (Integer outIndex : outIndexes) {
                if (!memo[outIndex]) {
                    queue.offer(outIndex);
                    ans++;
                }
            }

            List<Integer> inIndexes = ins[index];
            for (Integer inIndex : inIndexes) {
                if (!memo[inIndex]) {
                    queue.offer(inIndex);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        LeetCode_1466_1_重新规划路线 t = new LeetCode_1466_1_重新规划路线();
        t.minReorder(5, LeetCodeUtils.convertMatrix("[[4,3],[2,3],[1,2],[1,0]]"));
    }
}
