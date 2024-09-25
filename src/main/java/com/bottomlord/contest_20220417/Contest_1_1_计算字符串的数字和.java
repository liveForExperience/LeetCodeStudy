package com.bottomlord.contest_20220417;

/**
 * @author chen yue
 * @date 2022-04-17 10:30:59
 */
public class Contest_1_1_计算字符串的数字和 {
    public String digitSum(String s, int k) {
        int n = s.length();
        if (n <= k) {
            return s;
        }

        while (s.length() > k) {
            int index = 0;
            StringBuilder sb = new StringBuilder();
            while (index < s.length()) {
                sb.append(getStr(index, s, k));
                index += k;
            }
            s = sb.toString();
        }

        return s;
    }

    private String getStr(int index, String str, int k) {
        String s = str.substring(index, index + Math.min(k, str.length() - index));
        char[] cs = s.toCharArray();
        int sum = 0;
        for (char c : cs) {
            sum += c - '0';
        }
        return Integer.toString(sum);
    }
}
