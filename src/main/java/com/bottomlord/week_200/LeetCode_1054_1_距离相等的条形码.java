package com.bottomlord.week_200;

import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2023-05-14 11:52:18
 */
public class LeetCode_1054_1_距离相等的条形码 {
    public int[] rearrangeBarcodes(int[] barcodes) {
        int n = barcodes.length;
        int[] bucket = new int[10001];
        for (int barcode : barcodes) {
            bucket[barcode]++;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] == 0) {
                continue;
            }

            queue.offer(new int[]{i, bucket[i]});
        }

        int[] ans = new int[n];
        int i = 0;
        while (!queue.isEmpty()) {
            int[] one = queue.poll(), two = queue.poll();
            ans[i++] = one[0];
            one[1]--;
            if (one[1] != 0) {
                queue.offer(one);
            }

            if (two == null) {
                continue;
            }

            ans[i++] = two[0];
            two[1]--;
            if (two[1] != 0) {
                queue.offer(two);
            }
        }

        return ans;
    }
}
