package com.bottomlord.contest_20220508;

/**
 * @author chen yue
 * @date 2022-05-08 10:28:55
 */
public class Contest_1_1_字符串中最大的3位相同数字 {
    public String largestGoodInteger(String num) {
        String[] strs = new String[]{
                "999", "888",
                "777","666",
                "555","444",
                "333","222",
                "111","000"
        };

        for (String str : strs) {
            if (num.contains(str)) {
                return str;
            }
        }

        return "";
    }
}
