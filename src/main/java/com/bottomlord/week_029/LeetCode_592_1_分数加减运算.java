package com.bottomlord.week_029;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/1/20 17:13
 */
public class LeetCode_592_1_分数加减运算 {
    public String fractionAddition(String expression) {
        List<Character> operators = new ArrayList<>();
        for (int i = 1; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-') {
                operators.add(c);
            }
        }

        List<Integer> numerators = new ArrayList<>();
        List<Integer> denominators = new ArrayList<>();
        for (String a : expression.split("\\+")) {
            for (String b : a.split("-")) {
                if (b.length() > 0) {
                    String[] strs = b.split("/");
                    numerators.add(Integer.parseInt(strs[0]));
                    denominators.add(Integer.parseInt(strs[1]));
                }
            }
        }

        if (expression.charAt(0) == '-') {
            numerators.set(0, -numerators.get(0));
        }

        int lcm = 1;
        for (int denominator : denominators) {
            lcm = lcm(denominator, lcm);
        }

        int numerator = lcm / denominators.get(0) * numerators.get(0);
        for (int i = 1; i < numerators.size(); i++) {
            if (operators.get(i - 1) == '+') {
                numerator += lcm / denominators.get(i) * numerators.get(i);
            } else {
                numerator -= lcm / denominators.get(i) * numerators.get(i);
            }
        }

        int gcd = gcd(Math.abs(numerator), Math.abs(lcm));

        return (numerator / gcd) + "/" + (lcm / gcd);
    }

    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}