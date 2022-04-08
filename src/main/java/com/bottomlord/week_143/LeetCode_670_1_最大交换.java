package com.bottomlord.week_143;

/**
 * @author chen yue
 * @date 2022-04-08 19:55:51
 */
public class LeetCode_670_1_最大交换 {
    public int maximumSwap(int num) {
        String str = Integer.toString(num);
        int n = str.length();
        char[] cs = str.toCharArray();

        for (int i = 0; i < n; i++) {
            int index = findMaxIndex(cs, i);
            char maxChar = str.charAt(index);

            if (cs[i] != maxChar) {
                cs[index] = cs[i];
                cs[i] = maxChar;
                break;
            }
        }

        return Integer.parseInt(new String(cs));
    }

    private int findMaxIndex(char[] cs, int start) {
        char maxChar = '0';
        int index = start;

        for (int i = start; i < cs.length; i++) {
            if (maxChar <= cs[i]) {
                maxChar = cs[i];
                index = i;
            }
        }

        return index;
    }
}
