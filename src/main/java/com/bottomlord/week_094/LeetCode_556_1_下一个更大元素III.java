package com.bottomlord.week_094;

/**
 * @author ChenYue
 * @date 2021/5/1 14:48
 */
public class LeetCode_556_1_下一个更大元素III {
    public int nextGreaterElement(int n) {
        char[] cs = ("" + n).toCharArray();
        boolean find = false;
        for (int i = cs.length - 2; i >= 0; i--) {
            if (cs[i] >= cs[i + 1]) {
                continue;
            }

            int index = i + 1;
            for (int j = i + 1; j < cs.length; j++) {
                if (cs[j] < cs[i]) {
                    break;
                }

                if (cs[j] <= cs[index] && cs[j] > cs[i]) {
                    index = j;
                }
            }

            swap(cs, i, index);
            int count = (cs.length - 1 - i) / 2;

            for (int j = 0; j < count; j++) {
                swap(cs, i + 1 + j, cs.length - 1 - j);
            }

            find = true;
            break;
        }

        if (!find) {
            return -1;
        }

        long sum = 0;
        for (char c : cs) {
            sum = sum * 10 + (c - '0');
        }

        return sum > Integer.MAX_VALUE ? -1 : (int) sum;
    }

    private void swap(char[] cs, int x, int y) {
        char c = cs[x];
        cs[x] = cs[y];
        cs[y] = c;
    }
}
