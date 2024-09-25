package com.bottomlord.week_040;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ChenYue
 * @date 2020/4/9 21:47
 */
public class Interview_1611_1_跳水板 {
    public int[] divingBoard(int shorter, int longer, int k) {
        if (k == 0) {
            return new int[0];
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < k; i++) {
            set.add(shorter * (k - i) + longer * i);
        }
        return set.stream().mapToInt(x -> x).sorted().toArray();
    }
}
