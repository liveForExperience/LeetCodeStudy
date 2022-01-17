# [LeetCode_1220_统计元音字母序列的数目](https://leetcode-cn.com/problems/count-vowels-permutation/)
## 解法
### 思路
动态规划：
- `dp[i][j]`：代表长度为i的字符串以j为结尾的个数
- 假设[a,e,i,o,u]使用数字对应为[0,1,2,3,4]
- 状态转移方程（根据题意可得）：
  - `dp[i][0] = dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][4]`
  - `dp[i][1] = dp[i - 1][0] + dp[i - 1][2]`
  - `dp[i][2] = dp[i - 1][1] + dp[i - 1][3]`
  - `dp[i][3] = dp[i - 1][2]`
  - `dp[i][4] = dp[i - 1][2] + dp[i - 1][3]`
- base case:
  - `dp[1][0] = 1`
  - `dp[1][1] = 1`
  - `dp[1][2] = 1`
  - `dp[1][3] = 1`
  - `dp[1][4] = 1`
- 状态压缩：因为i的值都是由i-1推导而得，所以这个维度可以省略，只处理dp[j]即可
- 获得结果：将5中状态的值累加即是结果，需要取模以防止过大
- 为了放置溢出，变量也需要声明为long类型
### 代码
```java
class Solution {
    public int countVowelPermutation(int n) {
        long[] dp = new long[5];
        for (int i = 0; i < 5; i++) {
            dp[i] = 1;
        }

        int mod = 1000000007;

        for (int j = 2; j <= n; j++) {
            long a = dp[0], e = dp[1], i = dp[2], o = dp[3], u = dp[4];

            dp[0] = (e + i + u) % mod;
            dp[1] = (a + i) % mod;
            dp[2] = (e + o) % mod;
            dp[3] = i % mod;
            dp[4] = (i + o) % mod;
        }

        long ans = 0;
        for (int i = 0; i < 5; i++) {
            ans = (ans + dp[i]) % mod;
        }
        return (int)ans;
    }
}
```