package com.bottomlord.week_001;

/**
 * @author LiveForExperience
 * @date 2019/7/9 12:05
 */
public class LeetCode_1108_1_IP地址无效化 {
    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        for (char c: address.toCharArray()) {
            if (c == '.') {
                sb.append('[');
                sb.append(c);
                sb.append(']');
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
