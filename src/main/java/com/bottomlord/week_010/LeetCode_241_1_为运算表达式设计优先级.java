package com.bottomlord.week_010;

import java.util.ArrayList;
import java.util.List;

public class LeetCode_241_1_为运算表达式设计优先级 {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '-' || c == '+' || c == '*') {
                List<Integer> lefts = diffWaysToCompute(input.substring(0, i));
                List<Integer> rights = diffWaysToCompute(input.substring(i + 1));

                int result = 0;
                for (int left : lefts) {
                    for (int right : rights) {
                        if (c == '-') {
                            result = left - right;
                        } else if (c == '+') {
                            result = left + right;
                        } else {
                            result = left * right;
                        }

                        ans.add(result);
                    }
                }
            }
        }

        if (ans.size() == 0) {
            ans.add(Integer.parseInt(input));
        }

        return ans;
    }
}
