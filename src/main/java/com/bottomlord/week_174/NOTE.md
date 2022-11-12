# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_940_不同子序列](https://leetcode.cn/problems/distinct-subsequences-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_790_多米诺和托米诺平铺](https://leetcode.cn/problems/domino-and-tromino-tiling/)
## 解法
### 思路
动态规划：
- dp[i][j]：第i列状态为j时候的方法数
  - i从1开始，结果从i=n中获取
  - j有4种状态
    - 0：全部填满
    - 1：上半部分填满
    - 2：下半部分填满
    - 3：没有
- 状态转移方程：
  - dp[0] = dp[0] + dp[1] + dp[2] + dp[3]
  - dp[1] = dp[3] + dp[2]
  - dp[2] = dp[3] + dp[1]
  - dp[3] = dp[0]
- base case：dp[0][0] = 1
- 返回结果：dp[n][0]
### 代码
```java
class Solution {
    public int numTilings(int n) {
        long[] dp = {1, 0, 0, 0};
        int mod = (int) 1e9 + 7;
        for (int i = 1; i <= n; ++i) {
            long[] g = new long[4];
            g[0] = (dp[0] + dp[1] + dp[2] + dp[3]) % mod;
            g[1] = (dp[2] + dp[3]) % mod;
            g[2] = (dp[1] + dp[3]) % mod;
            g[3] = dp[0];
            dp = g;
        }
        return (int) dp[0];
    }
}
```