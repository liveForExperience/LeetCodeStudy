package com.bottomlord.week_030;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ThinkPad
 * @date 2020/2/1 14:32
 */
public class LeetCode_93_1_复原IP地址 {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                for (int k = 1; k <= 3; k++) {
                    for (int l = 1; l <= 3; l++) {
                        if (i + j + k + l != s.length()) {
                            continue;
                        }

                        int a = Integer.parseInt(s.substring(0, i));
                        int b = Integer.parseInt(s.substring(i, i + j));
                        int c = Integer.parseInt(s.substring(i + j, i + j + k));
                        int d = Integer.parseInt(s.substring(i + j + k, i + j + k + l));

                        if (a <= 255 && b <= 255 && c <= 255 && d <= 255) {
                            StringBuilder ip = new StringBuilder();
                            ip.append(a).append(".").append(b).append(".").append(c).append(".").append(d);

                            if (ip.length() == s.length() + 3) {
                                ans.add(ip.toString());
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }
}