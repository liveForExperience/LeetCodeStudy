package com.bottomlord.week_4;

/**
 * @author LiveForExperience
 * @date 2019/8/4 13:13
 */
public class LeetCode_520_1_检测大写字母 {
    public boolean detectCapitalUse(String word) {
        if (word.length() <= 1) {
            return true;
        }

        boolean allBig = false, allSmall = false;
        int index = 0;

        char[] cs = word.toCharArray();
        char firstLetter = cs[0];

        if (isSmall(firstLetter)) {
            allSmall = true;
            index = 1;
        }

        if (isBig(firstLetter)) {
            char secontLetter = cs[1];
            if (isBig(secontLetter)) {
                allBig = true;
            }
            if (isSmall(secontLetter)) {
                allSmall = true;
            }
            index = 2;
        }

        for (int i = index; i < cs.length; i++) {
            if (allBig && isSmall(cs[i])) {
                return false;
            }

            if (allSmall && isBig(cs[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean isBig(char c) {
        return c <= 90 && c >= 65;
    }

    private boolean isSmall(char c) {
        return c <= 122 && c >= 97;
    }
}
