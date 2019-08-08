package com.bottomlord.week_5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeetCode_937_2 {
    public String[] reorderLogFiles(String[] logs) {
        List<String> alph = new ArrayList<>();
        List<String> num = new ArrayList<>();
        for (String log : logs) {
            int i = 0;
            while (log.charAt(i++) != ' ') ;
            if (log.charAt(i + 1) >= '0' && log.charAt(i + 1) <= '9') {
                num.add(log);
            } else {
                alph.add(log);
            }
        }

        alph.sort((a, b) -> {
            int i = 0, j = 0;
            while (a.charAt(i++) != ' ') ;
            while (b.charAt(j++) != ' ') ;

            int diff = a.substring(i).compareTo(b.substring(j));
            return diff != 0 ? diff : a.substring(0, i - 1).compareTo(b.substring(0, j - 1));
        });

        int i = 0;
        for (String log : alph) {
            logs[i++] = log;
        }
        for (String log : num) {
            logs[i++] = log;
        }

        return logs;
    }
}