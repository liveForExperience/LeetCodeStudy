package com.bottomlord.week_191;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author chen yue
 * @date 2023-03-07 08:48:46
 */
public class LeetCode_1092_1_花括号展开II {
    private TreeSet<String> set = new TreeSet<>();

    public List<String> braceExpansionII(String expression) {
        rescue(expression);
        return new ArrayList<>(set);
    }

    private void rescue(String expression) {
        int ri = expression.indexOf("}");
        if (ri == -1) {
            set.add(expression);
            return;
        }

        int li = ri - 1;
        for (int i = li; i >= 0; i--) {
            if (expression.charAt(i) == '{') {
                li = i;
                break;
            }
        }

        String left = expression.substring(0, li),
               mid = expression.substring(li + 1, ri),
               right = expression.substring(ri + 1);
        String[] midFactors = mid.split(",");

        for (String midFactor : midFactors) {
            rescue(left + mid + right);
        }
    }
}
