package com.bottomlord.week_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiveForExperience
 * @date 2019/7/21 17:42
 */
public class LeetCode_811_1_子域名访问统计 {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();

        for (String str : cpdomains) {
            String[] factors = str.split(" ");
            int count = Integer.parseInt(factors[0]);
            String rawDoamin = factors[1];

            String[] domains = rawDoamin.split("\\.");
            StringBuilder pre = new StringBuilder();
            for (int i = domains.length - 1; i >= 0; i--) {
                pre.insert(0, domains[i] + ".");

                if (pre.charAt(pre.length() - 1) == '.') {
                    pre.deleteCharAt(pre.length() - 1);
                }

                if (!map.containsKey(pre.toString())) {
                    map.put(pre.toString(), count);
                } else {
                    map.computeIfPresent(pre.toString(), (k, v) -> v += count);
                }
            }
        }

        List<String> ans = new ArrayList<>(map.size());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            ans.add(entry.getValue() + " " + entry.getKey());
        }
        return ans;
    }

    public static void main(String[] args) {
        LeetCode_811_1_子域名访问统计 test = new LeetCode_811_1_子域名访问统计();
        test.subdomainVisits(new String[]{"9001 discuss.leetcode.com"});
    }
}
