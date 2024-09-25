package com.bottomlord.week_116;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-10-03 05:12:18
 */
public class LeetCode_166_1_分数到小数 {
    public String fractionToDecimal(int numerator, int denominator) {
        long num = numerator, de = denominator;
        if (num % de == 0) {
            return String.valueOf(num / de);
        }

        StringBuilder sb = new StringBuilder();
        if (num < 0 ^ de < 0) {
            sb.append("-");
        }

        num = Math.abs(num);
        de = Math.abs(de);

        sb.append(num / de).append(".");

        StringBuilder sb1 = new StringBuilder();
        long reminder = num % de;
        int index = 0;
        Map<Long, Integer> mapping = new HashMap<>();

        while (reminder != 0 && !mapping.containsKey(reminder)) {
            mapping.put(reminder, index);
            reminder *= 10;
            sb1.append(reminder / de);
            reminder %= de;
            index++;
        }

        if (reminder != 0) {
            sb1.insert(index, "(");
            sb1.append(")");
        }

        return sb.append(sb1).toString();
    }
}
