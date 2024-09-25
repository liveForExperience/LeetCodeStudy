package com.bottomlord.week_036;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/3/10 12:48
 */
public class Interview_62_1_圆圈中最后剩下的数字 {
    public int lastRemaining(int n, int m) {
        if (n == 0) {
            return -1;
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        int index = (m - 1) % list.size();
        while (list.size() > 1) {
            list.remove(index);
            index = (index + m -  1) % list.size();
        }


        return list.get(0);
    }
}
