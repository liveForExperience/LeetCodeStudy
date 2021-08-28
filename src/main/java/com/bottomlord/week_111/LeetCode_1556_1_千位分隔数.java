package com.bottomlord.week_111;

/**
 * @author chen yue
 * @date 2021-08-28 11:16:07
 */
public class LeetCode_1556_1_千位分隔数 {
    public String thousandSeparator(int n) {
        String s = Integer.toString(n);
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.insert(0, s.charAt(i));
            index++;
            if (index == 3 && i != 0) {
                index = 0;
                sb.insert(0,  '.');
            }
        }

        return sb.toString();
    }
}
