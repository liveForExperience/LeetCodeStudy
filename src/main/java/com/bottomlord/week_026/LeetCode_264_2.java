package com.bottomlord.week_026;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/1/1 20:43
 */
public class LeetCode_264_2 {
    public int nthUglyNumber(int n) {
        List<Integer> list = new ArrayList<Integer>(){{add(1);}};
        int i2 = 0, i3 = 0, i5  =0, index = 1;
        while (index++ < n) {
            int num = Math.min(list.get(i2) * 2, Math.min(list.get(i3) * 3, list.get(i5) * 5));
            list.add(num);

            if (list.get(i2) * 2 == num) {
                i2++;
            }

            if (list.get(i3) * 3 == num) {
                i3++;
            }

            if (list.get(i5) * 5 == num) {
                i5++;
            }
        }

        return list.get(list.size() - 1);
    }
}