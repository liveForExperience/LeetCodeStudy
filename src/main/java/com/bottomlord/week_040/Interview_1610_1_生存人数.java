package com.bottomlord.week_040;

import java.util.Arrays;

/**
 * @author ChenYue
 * @date 2020/4/10 13:24
 */
public class Interview_1610_1_生存人数 {
    public int maxAliveYear(int[] birth, int[] death) {
        Arrays.sort(birth);
        Arrays.sort(death);
        int ans = 0, max = 0, cur = 0, b = 0, d = 0;
        while (b < birth.length) {
            if (birth[b] <= death[d]) {
                cur++;
                if (cur > max) {
                    max = cur;
                    ans = b;
                }
                b++;
            } else {
                cur--;
                d++;
            }
        }
        return birth[ans];
    }
}
