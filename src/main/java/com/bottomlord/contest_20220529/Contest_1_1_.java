package com.bottomlord.contest_20220529;

/**
 * @author chen yue
 * @date 2022-05-29 10:23:55
 */
public class Contest_1_1_ {
    public int rearrangeCharacters(String s, String target) {
        int[] bucket = new int[26];
        for (char c : s.toCharArray()) {
            bucket[c - 'a']++;
        }

        int ans = 0;
        while (true) {
            boolean flag = true;
            for (char c : target.toCharArray()) {
                if (bucket[c - 'a'] == 0) {
                    flag = false;
                    break;
                }

                bucket[c - 'a']--;
            }

            if (!flag) {
                break;
            }

            ans++;
        }

        return ans;
    }
}
