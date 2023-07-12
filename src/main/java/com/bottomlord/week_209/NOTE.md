# [LeetCode_1911_最大子序列交替和](https://leetcode.cn/problems/maximum-alternating-subsequence-sum/)
## 解法
### 思路
动态规划
- dp[i][j]：前i个元素的最大交替和。如果j为0，则最后一个元素是奇数坐标，如果j为1，则最后一个元素是偶数坐标
- 状态转移方程：
  - j = 0: dp[i][0] = max(dp[i-1][1] - nums[i], dp[i-1][0])
  - j = 1: dp[i][1] = max(dp[i-1][0] + nums[i], dp[i-1][1])
- base case：dp[0][0] = dp[0][1] = 0
- 结果：max(dp[n][0], dp[n][1])
### 代码
```java
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n + 1][2];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - nums[i - 1]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + nums[i - 1]);
        }
        
        return Math.max(dp[n][0], dp[n][1]);
    }
}
```