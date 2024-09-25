package com.bottomlord.week_085;

/**
 * @author ChenYue
 * @date 2021/2/25 19:16
 */
public class LeetCode_468_1_验证IP地址 {
    public String validIPAddress(String IP) {
        if (IP.chars().filter(x -> x == '.').count() == 3) {
            return validateIp4(IP) ? "Ipv4" : "Neither";
        }

        if (IP.chars().filter(x -> x == ':').count() == 7) {
            return validateIp6(IP) ? "Ipv6" : "Neither";
        }

        return "Neither";
    }

    private boolean validateIp4(String ip) {
        String[] strs = ip.split("\\.");
        for (String str : strs) {
            if (str.length() == 0 || str.length() > 3) {
                return false;
            }

            if (str.startsWith("0") && str.length() > 1) {
                return false;
            }

            int num;
            try {
                num = Integer.parseInt(str);
            } catch (Exception e) {
                return false;
            }

            if (num < 0 || num > 255) {
                return false;
            }
        }

        return true;
    }

    private boolean validateIp6(String ip) {
        String[] strs = ip.split(":");
        String hexdigits = "0123456789abcdefABCDEF";
        for (String str : strs) {
            if (str.length() == 0 || str.length() > 4) {
                return false;
            }

            for (char c : str.toCharArray()) {
                if (hexdigits.indexOf(c) == -1) {
                    return false;
                }
            }
        }

        return true;
    }
}
