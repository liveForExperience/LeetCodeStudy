package com.bottomlord.week_118;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-10-13 08:43:38
 */
public class LeetCode_412_2 {
    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            ans.add(get(i));
        }
        return ans;
    }

    private String get(int i) {
        if (i % 3 == 0 && i % 5 == 0) {
            return "FizzBuzz";
        } else if (i % 3 == 0) {
            return "Fizz";
        } else if (i % 5 == 0) {
            return "Buzz";
        } else {
            return "" + i;
        }
    }
}