# [LeetCode_1155_掷骰子等于目标和的方法数](https://leetcode.cn/problems/number-of-dice-rolls-with-target-sum)
## 解法
### 思路
动态规划：
- dp[i][j]：
  - `i`代表第`i`次掷骰子
  - `j`代表前`i`次掷骰子后的总和为`j`
  - `dp[i][j]`代表`i`次掷骰子之后得到总和为`j`时的所有组合次数
- 状态转移方程：`dp[i][j] += dp[i - 1][j - x], x ∈ [1, k]`
- base case：`dp[1][x] = 1, x ∈ [1, k]`
- 返回结果：`dp[n][target]`
- 算法过程：
  - 3层循环
    - 第1层从2开始遍历到n次，模拟掷骰子
    - 第2层遍历所有上一次计算得到的可能总和，范围在`[i - 1, target - (n - i + 1)]`
    - 第3层遍历骰子所有可能的面值
  - 循环内容套用状态转移方程进行计算
  - 遍历结束后返回`dp[n][target]`
### 代码
```java
class Solution {
    public int numRollsToTarget(int n, int k, int target) {
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 1; i <= Math.min(k, target); i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int preSum = i - 1; preSum <= target - (n - i + 1); preSum++) {
                for (int curValue = 1; curValue <= k; curValue++) {
                    int total;
                    if ((total = preSum + curValue) > target) {
                        break;
                    }

                    dp[i][total] += dp[i - 1][preSum];
                    dp[i][total] %= 1000000007;
                }
            }
        }

        return dp[n][target];
    }
}
```