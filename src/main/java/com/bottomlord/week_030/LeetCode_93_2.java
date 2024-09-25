package com.bottomlord.week_030;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/2/1 21:24
 */
public class LeetCode_93_2 {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        LinkedList<String> segments = new LinkedList<>();
        backTrack(s, segments, ans, -1, 3);
        return ans;
    }

    private void backTrack(String s, LinkedList<String> segments, List<String> ans, int prePos, int dots) {
        int maxPos = Math.min(s.length() - 1, prePos + 4);
        for (int curPos = prePos + 1; curPos < maxPos; curPos++) {
            String segment = s.substring(prePos + 1, curPos + 1);
            if (isValid(segment)) {
                segments.add(segment);
                if (dots > 1) {
                    backTrack(s, segments, ans, curPos, dots - 1);
                } else {
                    output(s, curPos, segments, ans);
                }
                segments.removeLast();
            }
        }
    }

    private boolean isValid(String segment) {
        return segment.length() <= 3 && (segment.charAt(0) == '0' ? segment.length() == 1 : Integer.parseInt(segment) <= 255);
    }

    private void output(String s, int curPos, LinkedList<String> segments, List<String> ans) {
        String segment = s.substring(curPos + 1);
        if (isValid(segment)) {
            segments.add(segment);
            ans.add(String.join(".", segments));
            segments.removeLast();
        }
    }
}
