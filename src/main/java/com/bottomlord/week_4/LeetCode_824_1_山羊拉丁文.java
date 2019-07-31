package com.bottomlord.week_4;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LiveForExperience
 * @date 2019/7/31 12:37
 */
public class LeetCode_824_1_山羊拉丁文 {
    public String toGoatLatin(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }

        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        set.add('A');
        set.add('E');
        set.add('I');
        set.add('O');
        set.add('U');

        String[] ss = S.split(" ");
        for (int i = 0; i < ss.length; i++) {
            String str = ss[i];
            StringBuilder sb = new StringBuilder();
            if (!set.contains(str.charAt(0))) {
                sb.append(str.substring(1)).append(str.charAt(0));
            } else {
                sb.append(str);
            }

            sb.append("ma");
            for (int j = 0; j < i + 1; j++) {
                sb.append("a");
            }

            ss[i] = sb.toString();
        }

        return String.join(" ", ss);
    }

    public static void main(String[] args) {
        System.out.println('a' + 0);
        System.out.println('A' + 0);
        System.out.println('z' + 0);
        System.out.println('Z' + 0);
    }
}
