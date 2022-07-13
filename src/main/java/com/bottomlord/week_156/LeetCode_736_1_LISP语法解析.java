package com.bottomlord.week_156;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author chen yue
 * @date 2022-07-06 09:19:35
 */
public class LeetCode_736_1_LISP语法解析 {
    private String s;
    private char[] cs;

    public int evaluate(String _s) {
        this.s = _s;
        this.cs = _s.toCharArray();
        return dfs(0, s.length() - 1, new HashMap<>());
    }

    private int dfs(int l, int r, Map<String, Integer> map) {
        char start = cs[l];
        if (start != '(') {
            String str = s.substring(l, r + 1);
            Integer value = map.get(str);
            return value == null ? Integer.parseInt(str) : value;
        }

        int index = ++l;
        r--;

        while (cs[index] != ' ') {
            index++;
        }

        String op = s.substring(l, index);
        index++;

        if (Objects.equals(op, "let")) {
            while (index <= r) {
                int mid = getNextIndex(index, r);
                if (mid >= r) {
                    return dfs(index, r, new HashMap<>(map));
                }

                String key = s.substring(index, mid);
                index = mid + 1;

                int mid2 = getNextIndex(index, r);
                map.put(key, dfs(index, mid2 - 1, new HashMap<>(map)));
                index = mid2 + 1;
            }

            return -1;
        } else {
            int mid = getNextIndex(index, r);
            int left = dfs(index, mid - 1, new HashMap<>(map)),
                right = dfs(mid + 1, r, new HashMap<>(map));

            return Objects.equals(op, "add") ? left + right : left * right;
        }
    }

    public int getNextIndex(int left, int end) {
        int right = left, score = 0;

        while (right <= end) {
            if (cs[right] == '(') {
                score++;
            } else if (cs[right] == ')') {
                score--;
            } else if (cs[right] == ' ') {
                if (score == 0) {
                    break;
                }
            }

            right++;
        }

        return right;
    }
}
