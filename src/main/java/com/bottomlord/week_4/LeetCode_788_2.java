package com.bottomlord.week_4;

/**
 * @author LiveForExperience
 * @date 2019/7/31 13:27
 */
public class LeetCode_788_2 {
    class Solution {
        public int rotatedDigits(int N) {
            int ans = 0;

            for (int i = 1; i <= N; i++) {
                if (judge(i)) {
                    ans++;
                }
            }

            return ans;
        }

        private boolean judge(int num) {
            String rule = "0182569";
            boolean ok = false;

            while (num != 0) {
                int m = num % 10;

                if (rule.indexOf((char) (m + '0')) < 0) {
                    return false;
                }

                if (m == 2 || m == 5 || m == 6 || m == 9) {
                    ok = true;
                }

                num /= 10;
            }

            return ok;
        }
    }
}
