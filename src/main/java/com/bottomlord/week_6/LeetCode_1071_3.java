package com.bottomlord.week_6;

public class LeetCode_1071_3 {
    public String gcdOfStrings(String str1, String str2) {
        int len = gcd(Math.max(str1.length(), str2.length()), Math.min(str1.length(), str2.length()));
        String ans = str1.substring(0, len);
        for (int i = 0; i < str1.length(); i += len) {
            if (!ans.equals(str1.substring(i, i + len))) {
                return "";
            }
        }

        for (int i = 0; i < str2.length(); i += len) {
            if (!ans.equals(str2.substring(i, i + len))) {
                return "";
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        int mod = a % b;
        return mod == 0 ? b : gcd(b, mod);
    }
}
