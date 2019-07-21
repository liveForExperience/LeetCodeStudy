package com.bottomlord.week_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiveForExperience
 * @date 2019/7/21 18:28
 */
public class LeetCode_811_2 {
    private static final String DOT = ".";
    private static final String SPACE = " ";
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();

        for (String str : cpdomains) {
            if (str.contains(SPACE)) {
                int count = Integer.parseInt(str.substring(0, str.indexOf(" ")));
                String domain = str.substring(str.indexOf(SPACE) + 1);

                putMap(map, count, domain);
                while (domain.contains(DOT)) {
                    domain = domain.substring(domain.indexOf(DOT) + 1);
                    putMap(map, count, domain);
                }
            }
        }

        List<String> ans = new ArrayList<>(map.size());
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            ans.add(entry.getValue() + SPACE + entry.getKey());
        }

        return ans;
    }

    private void putMap(Map<String, Integer> map, int count, String domain) {
        if (map.containsKey(domain)) {
            map.computeIfPresent(domain, (k, v) -> v += count);
        } else {
            map.put(domain, count);
        }
    }

    public static void main(String[] args) {
        LeetCode_811_2 test = new LeetCode_811_2();
        test.subdomainVisits(new String[]{"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"});
    }
}