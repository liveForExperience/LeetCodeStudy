package com.bottomlord.contest_20220529;

import com.bottomlord.contest_20220515.Contest_4_2;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author chen yue
 * @date 2022-05-29 10:24:00
 */
public class Contest_2_1_ {
    public String discountPrices(String sentence, int discount) {
        String[] strs = sentence.split(" ");

        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            if (isNum(str)) {
                long num = Long.parseLong(str.substring(1));
                String ns = new BigDecimal(num - (num  * (discount * 1D / 100))).setScale(2, RoundingMode.HALF_UP).toString();
                strs[i] = "$" + ns;
            }
        }

        return String.join(" ", strs);
    }

    private boolean isNum(String str) {
        if (str == null || str.length() <= 1) {
            return false;
        }

        if (str.charAt(0) != '$') {
            return false;
        }

        for (int i = 1; i < str.toCharArray().length; i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    private String getStr(String str) {
        if (!str.contains(".")) {
            return str + ".00";
        }

        int index = str.indexOf(".");
        String post = str.substring(index + 1);

        if (post.length() == 2) {
            return str;
        }

        if (post.length() == 1) {
            return str + "0";
        }

        int num = str.charAt(index + 3) - '0';
        int last = str.charAt(index + 2) - '0';

        return str.substring(0, index + 1) + str.charAt(index + 1) + (num >= 5 ? last + 1 : last);
    }

    public static void main(String[] args) {
        Contest_2_1_ t = new Contest_2_1_();
        t.discountPrices("706hzu76jjh7yufr5x9ot60v149k5 $7651913186 pw2o $6"
                ,28);
    }
}
