package com.bottomlord.week_121;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author chen yue
 * @date 2021-11-04 09:04:50
 */
public class LeetCode_367_2 {
    public boolean isPerfectSquare(int num) {
        BigDecimal l = new BigDecimal(1), r = new BigDecimal(num), n = new BigDecimal(num);
        while (l.compareTo(r) < 0) {
            BigDecimal mid = r.subtract(l);
            mid = mid.divide(BigDecimal.valueOf(2), RoundingMode.DOWN);
            mid = mid.add(l);

            BigDecimal target = mid.multiply(mid);

            if (target.equals(n)) {
                return true;
            } else if (target.compareTo(n) > 0) {
                l = mid;
            } else {
                r = mid;
            }
        }

        return false;
    }
}