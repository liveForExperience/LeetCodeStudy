package com.bottomlord.week_009;

public class LeetCode_434_3 {
    public int countSegments(String s) {
        char[] cs = s.toCharArray();
        int count = 0;
        for (int i = 0; i < cs.length;) {
            i = move2Start(i, cs);
            i = move2End(i, cs);
            count++;
        }

        return count;
    }

    private int move2Start(int i, char[] cs) {
        while (i < cs.length && cs[i] == ' ') {
            i++;
        }
        return i;
    }

    private int move2End(int i, char[] cs) {
        while (i < cs.length && cs[i] != ' ') {
            i++;
        }
        return i;
    }
}
