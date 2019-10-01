package com.bottomlord.week_013;

public class LeetCode_12_2 {
    private int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < num / nums[i]; j++) {
                sb.append(roman[i]);
            }
            num %= nums[i];
        }
        return sb.toString();
    }
}