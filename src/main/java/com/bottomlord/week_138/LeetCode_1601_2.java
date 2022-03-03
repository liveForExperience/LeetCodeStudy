package com.bottomlord.week_138;

/**
 * @author chen yue
 * @date 2022-03-01 22:02:35
 */
public class LeetCode_1601_2 {
    public int maximumRequests(int n, int[][] requests) {
        return dfs(requests, 0, new int[n], 0);
    }

    private int dfs(int[][] requests, int index, int[] bucket, int count) {
        if (index == requests.length) {
            if (check(bucket)) {
                return count;
            } else {
                return 0;
            }
        }

        int ans = dfs(requests, index + 1, bucket, count);

        int x = requests[index][0], y = requests[index][1];
        bucket[x]--;
        bucket[y]++;
        ans = Math.max(dfs(requests, index + 1, bucket, count + 1), ans);
        bucket[x]++;
        bucket[y]--;

        return ans;
    }

    private boolean check(int[] bucket) {
        for (int num : bucket) {
            if (num != 0) {
                return false;
            }
        }

        return true;
    }
}