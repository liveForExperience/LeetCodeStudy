package com.bottomlord.week_057;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/8/5 8:36
 */
public class LeetCode_166_1_分数到小数 {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        Map<Long, Integer> map = new HashMap<>();
        if (numerator < 0 ^ denominator < 0) {
            sb.append("-");
        }

        long n = Math.abs(Long.parseLong(String.valueOf(numerator))),
             d = Math.abs(Long.parseLong(String.valueOf(denominator)));

        long reminder = n % d;
        sb.append(n / d);
        if (reminder == 0) {
            return sb.toString();
        }
        sb.append(".");

        while (reminder != 0) {
            if (map.containsKey(reminder)) {
                sb.insert(map.get(reminder), "(").append(")");
                return sb.toString();
            }

            map.put(reminder, sb.length());
            n = reminder * 10;
            sb.append(n / d);
            reminder = n % d;
        }

        return sb.toString();
    }
}
