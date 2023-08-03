package com.bottomlord.week_212;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-08-03 23:02:10
 */
public class LeetCode_722_1_删除注释 {
    private int state = 0;
    private static final int NORMAL = 0, LINE = 1, BLOCK = 2;
    public List<String> removeComments(String[] source) {
        StringBuilder sb = new StringBuilder();
        List<String> ans = new ArrayList<>();
        for (String s : source) {
            if (isBlock()) {
                sb = new StringBuilder();
            }

            for (int i = 0; i < s.length();) {
                i = execute(s, i, sb);
            }

            if (sb.length() > 0 && !isBlock()) {
                ans.add(sb.toString());
            }
        }

        return ans;
    }

    private int execute(String str, int index, StringBuilder sb) {
        if (isBlock()) {
            if (isBlockEnd(str, index)) {
                state = NORMAL;
                return index + 2;
            }
        } else if (isLine()) {
            return str.length();
        } else {
            if (isBlockStart(str, index)) {
                state = BLOCK;
                return index + 2;
            } else if (isLine(str, index)) {
                return str.length();
            } else {
                sb.append(str.charAt(index));
            }
        }

        return index + 1;
    }

    private boolean isBlock() {
        return state == BLOCK;
    }

    private boolean isLine() {
        return state == LINE;
    }

    private boolean isBlockStart(String str, int index) {
        return doJudge(str, index, '/', '*');
    }

    private boolean isBlockEnd(String str, int index) {
        return doJudge(str, index, '*', '/');
    }

    private boolean isLine(String str, int index) {
        return doJudge(str, index, '/', '/');
    }

    private boolean doJudge(String str, int index, char first, char second) {
        return str.charAt(index) == first &&
                index + 1 < str.length() &&
                str.charAt(index + 1) == second;
    }
}
