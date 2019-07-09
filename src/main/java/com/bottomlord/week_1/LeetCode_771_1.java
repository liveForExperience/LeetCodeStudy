package com.bottomlord.week_1;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LiveForExperience
 * @date 2019/7/7 16:12
 */
public class LeetCode_771_1 {
    public int numJewelsInStones(String J, String S) {
        Set<Character> jSet = new HashSet<>(J.length());
        for (char c: J.toCharArray()) {
            jSet.add(c);
        }
        int count = 0;
        for (char c: S.toCharArray()) {
            if (jSet.contains(c)) {
                count++;
            }
        }
        return count;
    }
}
