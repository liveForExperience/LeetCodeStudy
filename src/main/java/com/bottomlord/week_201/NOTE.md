# [LeetCode_1335_工作计划的最低难度](https://leetcode.cn/problems/minimum-difficulty-of-a-job-schedule/)
## 解法
### 思路
- 特殊情况，当工作量少于d的时候，不符合题目要求，直接返回-1
- 初始化变量n，代表任务数。
- dp[i][j]：第i天完成第j个工作的最小难度值
- 状态转移方程：dp[i][j] = min(dp[i][j], max + dp[i - 1][k - 1]);
  - k∈[i, j]
  - max：k到j区间内的最大值
- 初始化dp[1][j]（j∈[1,n]）
  - 遍历获取区间内的最大值并赋值给对应的dp[1][j]，值的比较区间是[1, j]
- 3层循环
  - 第一层遍历天数
  - 第二层遍历从天数开始的任务数
  - 第三层从任务数开始倒序遍历，确定区间内的最大值，内部则进行状态转移方程的计算
- 最终返回dp[d][n]
### 代码
```java
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }

        int[][] dp = new int[d + 1][n + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, jobDifficulty[i]);
            dp[1][i + 1] = max;
        }

        for (int i = 2; i <= d; i++) {
            for (int j = i; j <= n; j++) {
                max = 0;
                for (int k = j; k >= i; k--) {
                    max = Math.max(max, jobDifficulty[k - 1]);
                    dp[i][j] = Math.min(dp[i][j], max + dp[i - 1][k - 1]);
                }
            }
        }

        return dp[d][n];
    }
}
```
## 解法二
### 思路
状态压缩
### 代码
```java
class Solution {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }

        int[] pre = new int[n + 1];
        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, jobDifficulty[i]);
            pre[i + 1] = max;
        }

        for (int i = 2; i <= d; i++) {
            int[] cur = new int[n + 1];
            Arrays.fill(cur, Integer.MAX_VALUE);
            for (int j = i; j <= n; j++) {
                max = 0;
                for (int k = j; k >= i; k--) {
                    max = Math.max(max, jobDifficulty[k - 1]);
                    cur[j] = Math.min(cur[j], max + pre[k - 1]);
                }
            }
            pre = cur;
        }

        return pre[n];
    }
}
```