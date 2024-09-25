package com.bottomlord.week_190;

/**
 * @author chen yue
 * @date 2023-02-28 08:45:14
 */
public class LeetCode_831_1_隐藏个人信息 {
    public String maskPII(String s) {
        s = s.toLowerCase();
        return isMailAddress(s) ? handleMailAddress(s) : handlePhoneNumber(s);
    }

    private String handleMailAddress(String s) {
        String name = s.substring(0, s.indexOf("@"));
        return name.charAt(0) + "*****" + name.charAt(name.length() - 1) + s.substring(s.indexOf("@"));
    }

    private String handlePhoneNumber(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }

        String countryCode = getStar(sb.length() - 10), number = sb.substring(sb.length() - 4);
        return countryCode.length() == 0 ? "***-***-" + number : "+" + countryCode + "-***-***-" + number;
    }

    private String getStar(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append("*");
        }
        return sb.toString();
    }

    private boolean isMailAddress(String s) {
        return s.contains("@");
    }
}
