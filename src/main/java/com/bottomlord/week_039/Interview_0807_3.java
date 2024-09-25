package com.bottomlord.week_039;

/**
 * @author ChenYue
 * @date 2020/3/31 22:40
 */
public class Interview_0807_3 {
    private int a = 0;

    public String[] permutation(String S) {
        String[] ans = new String[cal(S.length())];
        process(S.toCharArray(), 0, ans);
        return ans;
    }

    private void process(char[] cs, int index, String[] ans) {
        if (index == cs.length) {
            ans[a++] = new String(cs);
            return;
        }

        for (int i = index; i < cs.length; i++) {
            swap(cs, index, i);
            process(cs, index + 1, ans);
            swap(cs, index, i);
        }
    }

    private void swap(char[] cs, int x, int y) {
        if (x == y) {
            return;
        }

        char c = cs[x];
        cs[x] = cs[y];
        cs[y] = c;
    }

    private int cal(int len) {
        if (len <= 2) {
            return len;
        }

        return cal(len - 1) * len;
    }
}
