package com.bottomlord.week_078;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/1/6 12:34
 */
public class LeetCode_420_1_强密码检验器 {
    public int strongPasswordChecker(String password) {
        int len = password.length(), kind = 0;
        boolean num, lower, upper;
        num = lower = upper = false;
        char[] cs = password.toCharArray();
        List<Integer> continuesList = new ArrayList<>();

        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (!num && Character.isDigit(c)) {
                num = true;
                kind++;
            } else if (!lower && Character.isLowerCase(c)) {
                lower = true;
                kind++;
            } else if (!upper && Character.isUpperCase(c)) {
                upper = true;
                kind++;
            }

            int continues = 1;
            while (i < len - 1 && cs[i + 1] == c) {
                i++;
                continues++;
            }

            if (continues >= 3) {
                continuesList.add(continues);
            }
        }

        if (len >= 6 && len <= 20 && kind == 3 && continuesList.isEmpty()) {
            return 0;
        }

        int needChangeKind = 3 - kind;
        if (len >= 6 && len <= 20) {
            int needChangeContinusCount = 0;
            for (int count : continuesList) {
                needChangeContinusCount += count / 3;
            }
            return Math.max(needChangeContinusCount, needChangeKind);
        }

        if (len < 6) {
            int needInsert = 6 - len;
            return needInsert == 1 && continuesList.size() == 1 && continuesList.get(0) == 5 ? 2 : needInsert;
        }

        int needDelete = len - 20, continusDeleteCount = 0;
        for (int count : continuesList) {
            continusDeleteCount += (count - 2);
        }
        if (needDelete - continusDeleteCount >= 0) {
            return needDelete + needChangeKind;
        }

        int remain = needDelete;
        for (int i = 0; i < continuesList.size(); i++) {
            int count = continuesList.get(i);
            if (count % 3 == 0) {
                continuesList.set(i, count - 1);
                if (--remain == 0) {
                    break;
                }
            }
        }

        if (remain >= 2) {
            for (int i = 0; i < continuesList.size(); i++) {
                int count = continuesList.get(i);
                if ((count - 1) % 3 == 0) {
                    continuesList.set(i, count - 2);
                    if ((remain -= 2) < 2) {
                        break;
                    }
                }
            }
        }

        while (remain >= 3) {
            for (int i = 0; i < continuesList.size(); i++) {
                int count = continuesList.get(i);
                if ((count - 2) % 3 == 0) {
                    continuesList.set(i, count - 3);
                    if ((remain -= 3) < 3) {
                        break;
                    }
                }
            }
        }

        int needChangeContinusCount = 0;
        for (int count : continuesList) {
            needChangeContinusCount += count / 3;
        }

        return needDelete + Math.max(needChangeContinusCount, needChangeKind);
    }
}