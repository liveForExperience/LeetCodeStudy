package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/9 12:06
 */
public class Interview_58I_1_翻转单词顺序 {
    public String reverseWords(String s) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                StringBuilder word = new StringBuilder();
                for (; i < s.length(); i++) {
                    if (s.charAt(i) != ' ') {
                        word.append(s.charAt(i));
                    } else {
                        break;
                    }
                }

                ans.insert(0, word.append(' '));
            }
        }

        return ans.toString().trim();
    }
}
