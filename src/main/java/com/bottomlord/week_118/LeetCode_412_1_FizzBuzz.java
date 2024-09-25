package com.bottomlord.week_118;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-10-13 08:38:09
 */
public class LeetCode_412_1_FizzBuzz {
    public List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            if (i % 3 == 0) {
                sb.append("Fizz");
            }

            if (i % 5 == 0) {
                sb.append("Buzz");
            }

            list.add(sb.length() == 0 ? "" + i : sb.toString());
        }

        return list;
    }
}
