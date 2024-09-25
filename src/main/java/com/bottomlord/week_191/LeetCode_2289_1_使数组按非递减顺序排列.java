package com.bottomlord.week_191;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-03-09 23:17:55
 */
public class LeetCode_2289_1_使数组按非递减顺序排列 {
    public int totalSteps(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        boolean flag = true;
        int count = 0;
        while (flag) {
            flag = false;
            List<Integer> cur = new ArrayList<>();
            cur.add(list.get(0));

            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) >= list.get(i - 1)) {
                    cur.add(list.get(i));
                } else {
                    flag = true;
                }
            }

            if (flag) {
                count++;
            }

            list = cur;
        }

        return count;
    }
}
