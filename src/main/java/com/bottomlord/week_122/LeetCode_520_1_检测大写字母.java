package com.bottomlord.week_122;

/**
 * @author chen yue
 * @date 2021-11-14 23:57:22
 */
public class LeetCode_520_1_检测大写字母 {
    public boolean detectCapitalUse(String word) {
        if (word.length() == 1) {
            return true;
        }

        char first = word.charAt(0);
        boolean firstBig = Character.isUpperCase(first);
        if (firstBig) {
            boolean secondBig = Character.isUpperCase(word.charAt(1));
            if (secondBig) {
                for (int i = 2; i < word.toCharArray().length; i++) {
                    if (Character.isLowerCase(word.charAt(i))) {
                        return false;
                    }
                }
            } else {
                for (int i = 2; i < word.toCharArray().length; i++) {
                    if (Character.isUpperCase(word.charAt(i))) {
                        return false;
                    }
                }
            }
        } else {
            for (int i = 1; i < word.toCharArray().length; i++) {
                if (Character.isUpperCase(word.charAt(i))) {
                    return false;
                }
            }

        }
        return true;
    }
}
