package com.bottomlord.contest_20220522;

/**
 * @author chen yue
 * @date 2022-05-22 10:28:03
 */
public class Contest_1_1_字母在字符串中的百分比 {
    public int percentageLetter(String s, char letter) {
        int n = s.length(), count = 0;
        for (char c : s.toCharArray()) {
            if (c == letter) {
                count++;
            }
        }

        return count * 100 / n;
    }
}
