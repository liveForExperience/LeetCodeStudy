package com.bottomlord.week_051;

import java.util.List;

/**
 * @author ChenYue
 * @date 2020/6/25 17:38
 */
public class LeetCode_139_1_单词拆分 {
    public boolean wordBreak(String s, List<String> wordDict) {
        if ("".equals(s)) {
            return true;
        }

        boolean flag = false;
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                flag |= wordBreak(s.substring(word.length()), wordDict);
            }
        }

        return flag;
    }
}
