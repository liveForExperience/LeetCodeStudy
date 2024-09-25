package com.bottomlord.contest_20220501;

import java.math.BigDecimal;

/**
 * @author chen yue
 * @date 2022-05-01 00:34:14
 */
public class Contest_1_1_移除指定数字得到的最大结果 {
    public String removeDigit(String number, char digit) {
        BigDecimal max = new BigDecimal(0);
        char[] cs = number.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (digit != c) {
                continue;
            }

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < cs.length; j++) {
                if (j == i) {
                    continue;
                }

                sb.append(cs[j]);
            }

            BigDecimal cur = new BigDecimal(sb.toString());
            if (cur.compareTo(max) > 0) {
                max = cur;
            }
        }

        return max.toString();
    }
}
