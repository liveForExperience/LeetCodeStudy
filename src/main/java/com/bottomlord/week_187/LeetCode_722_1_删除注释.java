package com.bottomlord.week_187;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen yue
 * @date 2023-02-06 06:51:16
 */
public class LeetCode_722_1_删除注释 {
    private int status = 0, normal = 0, inline = 1, block = 1 << 1;

    public List<String> removeComments(String[] source) {
        List<String> ans = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (String str : source) {
            if (!blockStatus()) {
                sb = new StringBuilder();
            }

            for (int i = 0; i < str.length(); ) {
                i = execute(str, i, sb);
            }

            if (sb.length() > 0 && !blockStatus()) {
                ans.add(sb.toString());
            }
        }
        return ans;
    }

    private int execute(String str, int index, StringBuilder sb) {
        if (blockStatus()) {
            if (isBlockEnd(str, index)) {
                status = normal;
                return index + 2;
            }
        } else if (inlineStatus()) {
            status = normal;
            return str.length();
        } else {
            if (isBlockStart(str, index)) {
                status = block;
                return index + 2;
            } else if (isInline(str, index)) {
                return str.length();
            } else {
                sb.append(str.charAt(index));
            }
        }

        return index + 1;
    }

    private boolean isBlockStart(String str, int index) {
        return str.charAt(index) == '/' && index + 1 < str.length() && str.charAt(index + 1) == '*';
    }

    private boolean isBlockEnd(String str, int index) {
        return str.charAt(index) == '*' && index + 1 < str.length() && str.charAt(index + 1) == '/';
    }

    private boolean isInline(String str, int index) {
        return str.charAt(index) == '/' && index + 1 < str.length() && str.charAt(index + 1) == '/';
    }

    private boolean blockStatus() {
        return status == block;
    }

    private boolean inlineStatus() {
        return status == inline;
    }
}
