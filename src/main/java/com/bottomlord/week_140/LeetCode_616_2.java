package com.bottomlord.week_140;

/**
 * @author chen yue
 * @date 2022-03-19 13:44:45
 */
public class LeetCode_616_2 {
    public String addBoldTag(String s, String[] words) {
        int n = s.length();
        boolean[] bucket = new boolean[n];
        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            for (String word : words) {
                boolean flag = true;
                for (int j = 0; j < word.length(); j++) {
                    if (j + i >= n || cs[i + j] != word.charAt(j)) {
                        flag = false;
                        break;
                    }
                }

                if (!flag) {
                    continue;
                }

                for (int j = 0; j < word.length(); j++) {
                    bucket[i + j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bucket.length; i++) {
            int count = 0;
            StringBuilder curSb = new StringBuilder();
            while (i < bucket.length && bucket[i]) {
                count++;
                curSb.append(cs[i++]);
            }

            if (count == 0) {
                sb.append(cs[i++]);
            } else {
                sb.append("<b>").append(curSb).append("</b>");
            }
        }

        return sb.toString();
    }
}