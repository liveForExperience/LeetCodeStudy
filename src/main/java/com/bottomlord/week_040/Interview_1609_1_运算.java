package com.bottomlord.week_040;

/**
 * @author ChenYue
 * @date 2020/4/10 8:16
 */
public class Interview_1609_1_运算 {
    class Operations {

        public Operations() {

        }

        public int minus(int a, int b) {
            return a + (-b);
        }

        public int multiply(int a, int b) {
            if (a == 0 || b == 0) {
                return 0;
            }

            boolean negative = isNegative(a, b);

            a = Math.abs(a);
            b = Math.abs(b);

            int ans = 0;
            if (b <= 10) {
                for (int i = 0; i < b; i++) {
                    ans += a;
                }
                return negative ? -ans : ans;
            }

            String sb = String.valueOf(b);
            for (int i = sb.length() - 1; i >= 0; i--) {
                int bit = sb.charAt(i) - '0';
                int multi = multiply(a, bit);
                for (int j = 0; j < sb.length() - 1 - i; j++) {
                    multi = multiply(multi, 10);
                }
                ans += multi;
            }

            return negative ? -ans : ans;
        }

        public int divide(int a, int b) {
            if (b == 1) {
                return a;
            }

            if (b == -1) {
                return -a;
            }

            boolean negative = isNegative(a, b);
            a = Math.abs(a);
            b = Math.abs(b);
            if (a < b) {
                return 0;
            }

            if (a == b) {
                return negative ? -1 : 1;
            }

            int ans = 0, cur = 0;
            String sa = String.valueOf(a);
            for (int i = 0; i < sa.length(); i++) {
                int bit = sa.charAt(i) - '0', count = 0;
                cur = multiply(cur, 10) + bit;
                while (cur >= b) {
                    cur = minus(cur, b);
                    count++;
                }
                ans = multiply(ans, 10) + count;
            }

            return negative ? -ans : ans;
        }

        private boolean isNegative(int a, int b) {
            return (a < 0 && b > 0) || (a > 0 && b < 0);
        }
    }
}
