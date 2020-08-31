package com.bottomlord.week_061;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/8/31 8:20
 */
public class LeetCode_249_1_移位字符串数组 {
    public List<List<String>> groupStrings(String[] strings) {
        int len = strings.length;
        boolean[] memo = new boolean[len];
        List<List<String>> ans = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            if (memo[i]) {
                continue;
            }

            memo[i] = true;

            String s1 = strings[i];
            List<String> list = new ArrayList<String>(){{this.add(s1);}};

            for (int j = i + 1; j < len; j++) {
                String s2 = strings[j];
                if (memo[j] || s1.length() != s2.length()) {
                    continue;
                }

                int dis = (s1.charAt(0) - s2.charAt(0) + 26) % 26;
                boolean flag = true;
                for (int k = 1; k < s1.length(); k++) {
                    if ((s1.charAt(k) - s2.charAt(k) + 26) % 26 != dis) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    list.add(s2);
                    memo[j] = true;
                }
            }

            ans.add(list);
        }

        return ans;
    }
}
