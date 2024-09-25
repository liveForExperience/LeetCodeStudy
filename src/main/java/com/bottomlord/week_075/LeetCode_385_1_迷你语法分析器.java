package com.bottomlord.week_075;

import com.bottomlord.NestedInteger;

/**
 * @author ChenYue
 * @date 2020/12/16 8:33
 */
public class LeetCode_385_1_迷你语法分析器 {
    private int index;
    private char[] cs;
    public NestedInteger deserialize(String s) {
        if (s == null || s.length() == 0) {
            return new NestedInteger();
        }

        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.parseInt(s));
        }

        index = 0;
        cs = s.toCharArray();
        return doDeserialize();
    }

    private NestedInteger doDeserialize() {
        NestedInteger ni = new NestedInteger();

        boolean negative = false;
        int num = 0;
        while (index < cs.length) {
            index++;

            if (cs[index] == ',') {
            } else if (cs[index] == '[') {
                ni.add(doDeserialize());
            } else if (cs[index] == ']') {
                return ni;
            } else if (cs[index] == '-') {
                negative = true;
            } else {
                num = num * 10 + (negative ? -Integer.parseInt("" + cs[index]) : Integer.parseInt("" + cs[index]));
                if (index < cs.length && (cs[index + 1] == ']' || cs[index] == ',')) {
                    ni.add(new NestedInteger(num));
                    negative = false;
                    num = 0;
                }
            }
        }

        return ni;
    }
}
