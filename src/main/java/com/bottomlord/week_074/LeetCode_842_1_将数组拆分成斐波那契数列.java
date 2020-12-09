package com.bottomlord.week_074;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/12/8 8:34
 */
public class LeetCode_842_1_将数组拆分成斐波那契数列 {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> ans = new ArrayList<>();
        backTrack(S, 0, ans, 0, 0);
        return ans;
    }

    private boolean backTrack(String s, int index, List<Integer> list, int sum, int pre) {
        if (index == s.length()) {
            return list.size() >= 3;
        }

        long curLong = 0L;
        for (int i = index; i < s.length(); i++) {
            if (i > index && s.charAt(index) == '0') {
                return false;
            }

            curLong = curLong * 10 + (s.charAt(i) - '0');
            if (curLong > Integer.MAX_VALUE) {
                return false;
            }

            int cur = (int) curLong;
            if (list.size() > 1) {
                if (cur > sum) {
                    return false;
                }

                if (cur < sum) {
                    continue;
                }
            }

            list.add(cur);
            if (backTrack(s, i + 1, list, pre + cur, cur)) {
                return true;
            } else {
                list.remove(list.size() - 1);
                return false;
            }
        }

        return false;
    }
}
