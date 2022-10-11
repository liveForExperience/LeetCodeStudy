# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_801_使序列递增的最小交换次数](https://leetcode.cn/problems/minimum-swaps-to-make-sequences-increasing/)
## 解法
### 思路
动态规划：
- dp[i][j]：
  - dp[i][0]:到i位置为止，不交换时保持升序的最少次数
  - dp[i][1]:到i位置为止，交换时保持升序的最少次数
- 状态转移方程:
  - `nums1[i] == nums2[i]`:
    - `dp[i][0] = dp[i][1] = min(dp[i - 1][0], dp[i - 1][1])`
  - `nums1[i] > nums1[i - 1] && nums2[i] > nums2[i - 1]`:
    - 不交换:`dp[i][0] = min(dp[i][0], dp[i - 1][0])`
    - 交换两次:`dp[i][1] = min(dp[i][1], dp[i - 1][1] + 1)`
  - `nums1[i] > nums2[i - 1] && nums2[i] > nums1[i - 1]`:
    - 交换一次:
      - `dp[i][0] = min(dp[i][0], dp[i - 1][1])`
      - `dp[i][1] = min(dp[i][1], dp[i - 1][0] + 1)`
- base case
  - dp[0][0] = 0
  - dp[0][1] = 1
### 代码
```java

```