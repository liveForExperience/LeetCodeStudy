package com.bottomlord.week_045;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/5/13 8:21
 */
public class Interview_1718_1_最短超串 {
    public int[] shortestSeq(int[] big, int[] small) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : small) {
            map.put(i, -1);
        }

        int count = small.length;
        int[] ans = new int[]{0, big.length - 1};

        for (int i = 0; i < big.length; i++) {
            int num = big[i];
            if (map.containsKey(num)) {
                if (map.get(num) == -1) {
                    count--;
                }

                map.put(num, i);
            }

            if (count <= 0) {
                int min = Collections.min(map.values());
                if (i - min < ans[1] - ans[0]) {
                    ans[0] = min;
                    ans[1] = i;
                }
            }
        }

        if (count > 0) {
            return new int[0];
        }

        return ans;
    }
}
