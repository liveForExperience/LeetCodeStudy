package com.bottomlord.week_002;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LiveForExperience
 * @date 2019/7/17 21:19
 */
public class LeetCode_929_2 {
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();

        for (String email : emails) {
            boolean ignore = false;
            boolean append = false;
            StringBuilder sb = new StringBuilder();
            for (char c: email.toCharArray()) {
                if (append) {
                    sb.append(c);
                } else {
                    if (c == '@') {
                        sb.append(c);
                        append = true;
                        continue;
                    }

                    if (ignore) {
                        continue;
                    }

                    if (c == '.') {
                        continue;
                    }

                    if (c == '+') {
                        ignore = true;
                        continue;
                    }

                    sb.append(c);
                }
            }

            set.add(sb.toString());
        }

        return set.size();
    }
}