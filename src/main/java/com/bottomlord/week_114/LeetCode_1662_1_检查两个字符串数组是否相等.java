package com.bottomlord.week_114;

/**
 * @author chen yue
 * @date 2021-09-13 12:17:51
 */
public class LeetCode_1662_1_检查两个字符串数组是否相等 {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();
        for (String s : word1) {
            sb1.append(s);
        }

        for (String s : word2) {
            sb2.append(s);
        }

        return sb1.toString().equals(sb2.toString());
    }
}
