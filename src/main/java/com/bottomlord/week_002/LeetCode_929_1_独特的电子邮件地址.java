package com.bottomlord.week_002;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LiveForExperience
 * @date 2019/7/17 18:28
 */
public class LeetCode_929_1_独特的电子邮件地址 {
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();

        for (String email: emails) {
            String[] factors = email.split("@");
            String name = factors[0];
            if (name.contains("+")) {
                name = name.substring(0, name.indexOf("+"));
            }
            name = name.replaceAll("\\.", "");
            set.add(name + "@" + factors[1]);
        }

        return set.size();
    }
}
