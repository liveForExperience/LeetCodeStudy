package com.bottomlord.week_029;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/1/21 9:03
 */
public class LeetCode_592_2 {
    public String fractionAddition(String expression) {
        List<Character> operators = new ArrayList<>();
        if (expression.charAt(0) != '-') {
            operators.add('+');
        }

        for (char c : expression.toCharArray()) {
            if (c == '+' || c == '-') {
                operators.add(c);
            }
        }

        int preNumerator = 0, preDenominator = 1, index = 0;
        for (String str : expression.split("(\\+)|(-)")) {
            if (str.length() > 0) {
                String[] factorials = str.split("/");
                int numerator = Integer.parseInt(factorials[0]);
                int denominator = Integer.parseInt(factorials[1]);

                int gcd = Math.abs(gcd(preDenominator, denominator));
                if (operators.get(index++) == '+') {
                    preNumerator = preNumerator * denominator / gcd + numerator * preDenominator / gcd;
                } else {
                    preNumerator = preNumerator * denominator / gcd - numerator * preDenominator / gcd;
                }

                preDenominator = preDenominator * denominator / gcd;

                gcd = Math.abs(gcd(preNumerator, preDenominator));
                preNumerator /= gcd;
                preDenominator /= gcd;
            }
        }

        return preNumerator + "/" + preDenominator;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}