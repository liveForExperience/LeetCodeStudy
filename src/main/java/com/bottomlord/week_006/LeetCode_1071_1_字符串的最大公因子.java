package com.bottomlord.week_006;

public class LeetCode_1071_1_字符串的最大公因子 {
    public String gcdOfStrings(String str1, String str2) {
        boolean longer = str1.length() > str2.length();
        String longS = longer ? str1 : str2;
        String shortS = longer ? str2 : str1;

        while (!shortS.equals("")) {
            if (!longS.contains(shortS)) {
                return "";
            }

            String tmp = longS.replace(shortS, "");
            longS = shortS;
            shortS = tmp;
        }

        return longS;
    }
}
