# LeetCode_1039_多边形三角剖分的最低得分
## 题目
给定 N，想象一个凸 N 边多边形，其顶点按顺时针顺序依次标记为 A[0], A[i], ..., A[N-1]。

假设您将多边形剖分为 N-2 个三角形。对于每个三角形，该三角形的值是顶点标记的乘积，三角剖分的分数是进行三角剖分后所有 N-2 个三角形的值之和。

返回多边形进行三角剖分后可以得到的最低分。

示例 1：
```
输入：[1,2,3]
输出：6
解释：多边形已经三角化，唯一三角形的分数为 6。
```
示例 2：
```
输入：[3,7,4,5]
输出：144
解释：有两种三角剖分，可能得分分别为：3*7*5 + 4*5*7 = 245，或 3*4*5 + 3*4*7 = 144。最低分数为 144。
```
示例 3：
```
输入：[1,3,1,4,1,5]
输出：13
解释：最低分数三角剖分的得分情况为 1*1*3 + 1*1*4 + 1*1*5 + 1*1*1 = 13。
```
提示：
```
3 <= A.length <= 50
1 <= A[i] <= 100
```
## 解法
### 思路
动态规划：
- 对于点`0，1，...，n-1`构成的多边形三角剖分，考虑点`0`和`n-1`，因为总有某个点`j`与点`0`和`n-1`构成三角形，使得原多边形被分成一个三角形和至多两个凸多边形，求解原问题退化成求解两个规模更小的子问题。
- `dp[i][j]`：i到j范围内，最小的值乘积
- 状态转移方程：`dp[i][j] = dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]`
- 三层循环确定`i,k,j`
    - 最外层确定`i`和`j`之间的距离`len`，最小是2
    - 中间层确定`i`，初始值是0
    - 根据最外层的`len`来确定`j`
    - 最内层来确定`k`，k的范围是在`i`和`j`之间，且因为会出现循环，所以要使用取余的方式来求得正确的下标
### 代码
```java
class Solution {
    public int minScoreTriangulation(int[] A) {
        int len = A.length;
        int[][] dp = new int[len][len];
        for (int[] arr : dp) {
            Arrays.fill(arr, Integer.MAX_VALUE);
        }
        for (int i = 0; i < len; i++) {
            dp[i][(i + 1) % len] = 0;
        }
        for (int l = 2; l < len; l++) {
            for (int i = 0; i < len; i++) {
                int j = (i + l) % len;
                for (int k = (i + 1) % len; k != j; k = (k + 1) % len) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[k] * A[j]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
```
## 解法二
### 思路
根据解法一中的分析，整个计算过程可以通过分解成若干子问题来解决，分解的过程是通过在首尾节点中找一个节点作为中点，组成一个三角形，从而将整个凸多边形分成至多2个。
- 且dfs过程中需要使用memo缓存，否则会超时
### 代码
```java
class Solution {
    public int minScoreTriangulation(int[] A) {
        Map<String, Integer> memo = new HashMap<>();
        return dfs(0, A.length - 1, A, memo);
    }

    private int dfs(int left, int right, int[] arr, Map<String, Integer> memo) {
        if (left + 1 == right) {
            return 0;
        }
        
        String key = Arrays.toString(new int[]{left, right});
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = left + 1; i < right; i++) {
            ans = Math.min(ans, dfs(left, i, arr, memo) + dfs(i, right, arr, memo) + arr[left] * arr[i] * arr[right]);
        }
        
        memo.put(key, ans);
        return ans;
    }
}
```