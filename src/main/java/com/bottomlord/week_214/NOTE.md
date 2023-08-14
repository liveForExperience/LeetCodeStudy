# [LeetCode_1595_连通两组点的最小成本](https://leetcode.cn/problems/minimum-cost-to-connect-two-groups-of-points/description/)
## 解法
### 思路
- 定义`dp[i][j]`来表示：第一组前i个元素全部已连接的情况在，第二组j状态时的最小成本
  - j状态是通过一个二进制整数来表示，k位为1代表已连接，为0代表未连接
- 状态转移方程
  - 第二组第k个节点只和i连接：
    - `dp[i][j] = min(dp[i][j], dp[i][j ^ (1 << k)] + cost[i - 1][k])`
    - `dp[i][j] = min(dp[i][j], dp[i - 1][j ^ (1 << k)] + cost[i - 1][k])`
  - 第二组第k个节点和i及其他节点连接：
    - `dp[i][j] = min(dp[i][j], dp[i - 1][j])`
- base case：
  - `dp[0][0] = 0`
  - 其他值初始化为极值
- 答案：`dp[m][(1 << n) - 1]`
### 代码
```java
class Solution {
    public int connectTwoGroups(List<List<Integer>> cost) {
        int m = cost.size(), n = cost.get(0).size(), inf = 1 << 30;
        int[][] dp = new int[m + 1][1 << n];
        for (int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], inf);
        }
        
        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < (1 << n); j++) {
                for (int k = 0; k < n; k++) {
                    if ((j >> k & 1) == 1) {
                        int c = cost.get(i - 1).get(k);
                        dp[i][j] = Math.min(dp[i][j], dp[i][j ^ (1 << k)] + c);
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + c);
                        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j ^ (1 << k)] + c);
                    }
                }
            }
        }
        
        return dp[m][(1 << n) - 1];
    }
}
```