package com.bottomlord.week_8;

public class LeetCode_482_1_密钥的格式化 {
    public String licenseKeyFormatting(String S, int K) {
        S = S.replaceAll("-" , "");
        int mod = S.length() % K;
        if (mod == 0) {
            mod = K;
        }

        if (S.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder(S.substring(0, mod));
        while (mod + K <= S.length()) {
            sb.append("-").append(S.substring(mod, mod + K).toUpperCase());
            mod += K;
        }

        return sb.toString();
    }
}
