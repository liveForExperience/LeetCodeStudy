package com.bottomlord.week_099;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/5/31 8:29
 */
public class LeetCode_1134_1_阿姆斯特朗数 {
    public boolean isArmstrong(int n) {
        int bit = 0, num = n;
        List<Integer> list = new ArrayList<>();
        while (num != 0) {
            bit++;
            int a = num % 10;
            list.add(a);
            num /= 10;
        }

        int sum = 0;
        for (Integer c : list) {
            sum += (int) Math.pow(c, bit);
        }

        return sum == n;
    }
}
