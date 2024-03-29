package com.bottomlord.week_118;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-10-11 08:38:51
 */
public class LeetCode_273_1_整数转换英文表示 {
    private Map<Integer, String> map = new HashMap<>();
    private Map<Integer, String> map2 = new HashMap<>();

    {
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, "Four");
        map.put(5, "Five");
        map.put(6, "Six");
        map.put(7, "Seven");
        map.put(8, "Eight");
        map.put(9, "Nine");
        map.put(10, "Ten");
        map.put(11, "Eleven");
        map.put(12, "Twelve");
        map.put(13, "Thirteen");
        map.put(14, "Fourteen");
        map.put(15, "Fifteen");
        map.put(16, "Sixteen");
        map.put(17, "Seventeen");
        map.put(18, "Eighteen");
        map.put(19, "Nineteen");
        map.put(20, "Twenty");
        map.put(30, "Thirty");
        map.put(40, "Forty");
        map.put(50, "Fifty");
        map.put(60, "Sixty");
        map.put(70, "Seventy");
        map.put(80, "Eighty");
        map.put(90, "Ninety");
        map2.put(0, "");
        map2.put(1, "Thousand");
        map2.put(2, "Million");
        map2.put(3, "Billion");
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        int index = 0;
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            int cur = num % 1000;
            if (cur != 0) {
                sb.insert(0, " ");
                sb.insert(0, map2.get(index));

                int n = cur % 100;
                if (n == 0) {

                } else if (map.containsKey(n)) {
                    sb.insert(0, " ");
                    sb.insert(0, map.get(n));
                } else {
                    sb.insert(0, " ");
                    sb.insert(0, map.get(n % 10));
                    sb.insert(0, " ");
                    sb.insert(0, map.get(n / 10 * 10));
                }

                if (cur / 100 * 100 != 0) {
                    sb.insert(0, " ");
                    sb.insert(0, map.get(cur / 100) + " Hundred");
                }
            }

            num /= 1000;
            index++;
        }

        return sb.toString().trim();
    }
}
