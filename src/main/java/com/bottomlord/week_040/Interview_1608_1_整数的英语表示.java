package com.bottomlord.week_040;

/**
 * @author ChenYue
 * @date 2020/4/9 12:24
 */
public class Interview_1608_1_整数的英语表示 {
    private String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Eleven","Twelve", "Thirteen", "Fourteen", "Fifteen",
            "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] gens = {"Billion", "Million", "Thousand", ""};
    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        String ans = "";
        int[] factors = new int[]{1000000000, 1000000, 1000, 1};
        for (int i = 0; i < factors.length; i++) {
            ans += recurse(num / factors[i]);
            if (num / factors[i] != 0) {
                ans += " " + gens[i] + " ";
            }
            num %= factors[i];
        }

        return ans;
    }

    private String recurse(int num) {
        if (num < 20) {
            return ones[num];
        }

        if (num < 100) {
            String ans = tens[num / 10];
            if (num % 10 != 0) {
                ans += " " + ones[num % 10];
            }
            return ans;
        }

        String ans = ones[num / 100] + " Hundred";
        if (num % 100 != 0) {
            ans += " " + recurse(num % 100);
        }
        return ans;
    }
}
