package com.bottomlord.week_026;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ThinkPad
 * @date 2020/1/1 19:49
 */
public class LeetCode_264_1_丑数II {
    public int nthUglyNumber(int n) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> no = new HashSet<>();
        int num = 0;
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(5);

        for (int i = 0; true; i++) {
            int tmp = i;
            boolean flag = true;
            while (tmp > 0) {
                if (set.contains(tmp)) {
                    break;
                }

                if (no.contains(tmp)) {
                    flag = false;
                    break;
                }

                if (tmp % 2 == 0) {
                    tmp /= 2;
                    continue;
                }

                if (tmp % 3 == 0) {
                    tmp /= 3;
                    continue;
                }

                if (tmp % 5 == 0) {
                    tmp /= 5;
                    continue;
                }

                flag = false;
                break;
            }

            if (flag) {
                num++;
                if (num == n) {
                    return i;
                }
                set.add(i);
            } else {
                no.add(i);
            }
        }
    }
}
