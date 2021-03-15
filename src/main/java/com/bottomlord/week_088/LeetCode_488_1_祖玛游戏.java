package com.bottomlord.week_088;

/**
 * @author ChenYue
 * @date 2021/3/15 8:35
 */
public class LeetCode_488_1_祖玛游戏 {
    private int ans;
    private int[] bucket;

    public int findMinStep(String board, String hand) {
        ans = Integer.MAX_VALUE;
        bucket = new int[26];
        for (int i = 0; i < hand.length(); i++) {
            bucket[hand.charAt(i) - 'A']++;
        }

        backTrack(new StringBuilder(board), 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void backTrack(StringBuilder sb, int step) {
        if (step >= ans) {
            return;
        }

        if (sb.length() == 0) {
            ans = step;
            return;
        }

        for (int i = 0; i < sb.length(); i++) {
            int j = i;
            while (j + 1 < sb.length() && sb.charAt(j) == sb.charAt(j + 1)) {
                j++;
            }

            char c = sb.charAt(i);
            if (i == j && bucket[c - 'A'] >= 2) {
                StringBuilder cur = new StringBuilder(sb);
                cur.insert(i, c);
                eliminate(cur);
                bucket[c - 'A']--;
                backTrack(cur, step + 1);
                bucket[c - 'A']++;
            } else if (j - i == 1) {
                if (bucket[c - 'A'] >= 1) {
                    StringBuilder cur = new StringBuilder(sb);
                    cur.insert(i, c);
                    eliminate(cur);
                    bucket[c - 'A']--;
                    backTrack(cur, step + 1);
                    bucket[c - 'A']++;
                    continue;
                }

                for (int k = 0; k < bucket.length; k++) {
                    if (bucket[k] == 0 || k == (c - 'A')) {
                        continue;
                    }

                    StringBuilder cur = new StringBuilder(sb);
                    cur.insert(i + 1, (char) (k + 'A'));
                    bucket[k]--;
                    backTrack(cur, step + 1);
                    bucket[k]++;
                }
            }
        }
    }

    private void eliminate(StringBuilder sb) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < sb.length(); i++) {
                int j = i;
                while (j + 1 < sb.length() && sb.charAt(j) == sb.charAt(j + 1)) {
                    j++;
                }

                if (j - i >= 2) {
                    flag = true;
                    sb.delete(i, j + 1);
                }
            }
        }
    }
}
