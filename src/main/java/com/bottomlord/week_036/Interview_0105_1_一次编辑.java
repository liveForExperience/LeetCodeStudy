package com.bottomlord.week_036;

/**
 * @author ThinkPad
 * @date 2020/3/15 12:26
 */
public class Interview_0105_1_一次编辑 {
    public boolean oneEditAway(String first, String second) {
        int fLen = first.length(), sLen = second.length();
        if (Math.abs(fLen - sLen) > 1) {
            return false;
        }

        return backTrack(first, 0, fLen, second, 0, sLen, 0);
    }

    private boolean backTrack(String f, int fi, int fLen, String s, int si, int sLen, int count) {
        if (si == sLen && fi == fLen && count <= 1) {
            return true;
        }

        if (count > 1) {
            return false;
        }

        if (fi == fLen) {
            return backTrack(f, fi, fLen, s, si + 1, sLen, count + 1);
        }

        if (si == sLen) {
            return backTrack(f, fi + 1, fLen, s, si, sLen, count + 1);
        }

        if (f.charAt(fi) == s.charAt(si)) {
            return backTrack(f, fi + 1, fLen, s, si + 1, sLen, count);
        } else {
            return backTrack(f, fi + 1, fLen, s, si, sLen, count + 1) ||
                    backTrack(f, fi, fLen, s, si + 1, sLen, count + 1) ||
                    backTrack(f, fi + 1, fLen, s, si + 1, sLen, count + 1);
        }
    }
}
