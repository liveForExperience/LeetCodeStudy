package com.bottomlord.week_175;

import java.util.Objects;

/**
 * @author chen yue
 * @date 2022-11-25 21:10:09
 */
public class LeetCode_809_1_情感丰富的文字 {
    public int expressiveWords(String s, String[] words) {
        int ans = 0;
        for (String word : words) {
            if (check(word, s)) {
                ans++;
            }
        }

        return ans;
    }

    private boolean check(String word, String s) {
        int wl = word.length(), sl = s.length();
        if (wl > sl) {
            return false;
        }

        if (wl == sl) {
            return Objects.equals(word, s);
        }

        int wi = 0, si = 0;
        while (wi < wl && si < sl) {
            int wc = 0, sc = 0;
            char wchar = word.charAt(wi), schar = s.charAt(si);
            if (wchar != schar) {
                return false;
            }

            while (wi < wl && word.charAt(wi) == wchar) {
                wi++;
                wc++;
            }

            while (si < sl && s.charAt(si) == schar) {
                si++;
                sc++;
            }

            if (wc > sc) {
                return false;
            }

            if (wc != sc && sc < 3) {
                return false;
            }
        }

        return si == sl && wi == wl;
    }
}
