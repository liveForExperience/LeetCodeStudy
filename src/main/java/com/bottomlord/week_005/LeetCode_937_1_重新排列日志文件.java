package com.bottomlord.week_005;

import java.util.Arrays;

public class LeetCode_937_1_重新排列日志文件 {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (a, b) -> {
            String[] splitA = a.split(" ", 2);
            String[] splitB = b.split(" ", 2);

            boolean isDigitA = Character.isDigit(splitA[1].charAt(0));
            boolean isDigitB = Character.isDigit(splitB[1].charAt(0));

            if (!isDigitA && !isDigitB) {
                int diff = splitA[1].compareTo(splitB[1]);
                return diff != 0 ? diff : splitA[0].compareTo(splitB[0]);
            }

            return isDigitA ? (isDigitB ? 0 : 1) : -1;
        });
        return logs;
    }
}
