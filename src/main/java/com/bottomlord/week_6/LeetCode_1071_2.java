package com.bottomlord.week_6;

public class LeetCode_1071_2 {
    public String gcdOfStrings(String str1, String str2) {
        int len = gcd(Math.max(str1.length(), str2.length()), Math.min(str1.length(), str2.length()));
        String ans = str1.substring(0, len);
        if (!"".equals(str1.replaceAll(ans, "")) || !"".equals(str2.replaceAll(ans, ""))) {
            return "";
        }
        return ans;
    }

    private int gcd(int a, int b) {
        int mod = a % b;
        return mod == 0 ? b : gcd(b, mod);
    }
}