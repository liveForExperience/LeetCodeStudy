package com.bottomlord.week_057;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/8/8 11:02
 */
public class LeetCode_179_1_最大数 {
    public String largestNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        for (int num : nums) {
            list.add(String.valueOf(num));
        }

        list.sort((o1, o2) -> {
            String a = o1 + o2,
                   b = o2 + o1;

            return b.compareTo(a);
        });

        if (Objects.equals("0", list.get(0))) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str);
        }

        return sb.toString();
    }
}
