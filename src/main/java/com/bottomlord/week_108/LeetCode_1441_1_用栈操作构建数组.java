package com.bottomlord.week_108;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-08-08 20:34:26
 */
public class LeetCode_1441_1_用栈操作构建数组 {
    public List<String> buildArray(int[] target, int n) {
        int index = 0, num = 1;
        List<String> ops = new ArrayList<>();
        while (num <= n) {
            while (num != target[index]) {
                ops.add("Push");
                ops.add("Pop");
                num++;
            }

            ops.add("Push");
            index++;
        }

        return ops;
    }
}
