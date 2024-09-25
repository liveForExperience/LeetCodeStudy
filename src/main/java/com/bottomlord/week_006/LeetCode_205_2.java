package com.bottomlord.week_006;

public class LeetCode_205_2 {
    public boolean isIsomorphic(String s, String t) {
        int[] sMap = new int[128];
        int[] tMap = new int[128];

        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);

            if (sMap[sc] == tc) {
                continue;
            }

            if (sMap[sc] == 0) {
                if (tMap[tc] != 0) {
                    return false;
                }

                sMap[sc] = tc;
                tMap[tc] = sc;
                continue;
            }
            return false;
        }
        return true;
    }
}