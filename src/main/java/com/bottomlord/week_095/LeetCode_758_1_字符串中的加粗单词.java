package com.bottomlord.week_095;

/**
 * @author ChenYue
 * @date 2021/5/8 11:09
 */
public class LeetCode_758_1_字符串中的加粗单词 {
    public String boldWords(String[] words, String S) {
        int len = S.length();
        boolean[] bucket = new boolean[len];
        for (String word : words) {
            int index = 0;
            while (index != -1) {
                index = S.indexOf(word, index);
                if (index != -1) {
                    for (int i = 0; i < word.length(); i++) {
                        bucket[index + i] = true;
                    }
                    index++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len;) {
            if (bucket[i]) {
                sb.append("<b>");
                while (i < len) {
                    if (bucket[i]) {
                        sb.append(S.charAt(i));
                        i++;
                    } else {
                        break;
                    }
                }
                sb.append("</b>");
            } else {
                sb.append(S.charAt(i));
                i++;
            }
        }

        return sb.toString();
    }
}
