package com.bottomlord.week_138;

/**
 * @author chen yue
 * @date 2022-02-28 09:02:19
 */
public class LeetCode_1601_1_最多可达成的换楼请求数 {
    public int maximumRequests(int n, int[][] requests) {
        int m = requests.length, ans = 0;
        for (int i = 0; i < (1 << m); i++) {
            int count = count(i);
            if (count <= ans) {
                continue;
            }

            if (check(i, requests, n)) {
                ans = count;
            }
        }

        return ans;
    }

    private boolean check(int state, int[][] requests, int n) {
        int m = requests.length, sum = 0;
        int[] bucket = new int[n];
        for (int i = 0; i < m; i++) {
            if (((state >> i) & 1) == 1) {
                int x = requests[i][0], y = requests[i][1];
                if (++bucket[x] == 1) {
                    sum++;
                }

                if (--bucket[y] == 0) {
                    sum--;
                }
            }
        }

        return sum == 0;
    }

    private int count(int state) {
        int count = 0;
        while (state != 0) {
            state &= (state - 1);
            count++;
        }

        return count;
    }
}
