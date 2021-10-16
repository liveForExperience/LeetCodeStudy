package com.bottomlord.week_118;

/**
 * @author chen yue
 * @date 2021-10-16 14:45:33
 */
public class LeetCode_38_2 {
    private static String[] bucket = new String[30];
    static {
        for (int k = 1; k <= 30; k++) {
            StringBuilder ans = new StringBuilder("1");
            for (int i = 2; i <= k; i++) {
                StringBuilder sb = new StringBuilder();
                int len = ans.length(), count = 0;
                char start = ans.charAt(0);
                for (int j = 0; j < len; j++) {
                    if (ans.charAt(j) == start) {
                        count++;
                    } else {
                        sb.append(count).append(start);
                        start = ans.charAt(j);
                        count = 1;
                    }
                }

                sb.append(count).append(start);
                ans = sb;
            }

            bucket[k - 1] = ans.toString();
        }
    }

    public String countAndSay(int n) {
        return bucket[n - 1];
    }
}
