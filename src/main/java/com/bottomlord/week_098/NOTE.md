# [LeetCode_664_奇怪的打印机](https://leetcode-cn.com/problems/strange-printer/)
## 解法
### 思路
动态规划：
- 观察到的特征：
    - 如果只有1种单词，例“a”，那就只需要1次打印
    - 如果有2种单词，例“ab”，那就需要2次打印
    - 如果字符子串2头的字符相同，例“aba”，那也需要2次打印
    - 如果字符子串2头的字符不相同，例“abab”，那就需要3次打印，也就是在情况3的基础上选择一个子串+1
- `dp[i][j]`：i和j区间内需要打印的最少次数
- base case：单个字符的打印次数为1
- 状态转移方程：
    - 如果i和j对应的字符相同：`dp[i][j] = dp[i][j - 1]`
    - 如果i和j对应的字符不相同：`就找这个区间中两两组合的最优解`
- 返回结果：`dp[0][n - 1]`
### 代码
```java
class Solution {
    public int strangePrinter(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = j - i + 1;
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    }
                }
            }
        }
        
        return dp[0][len - 1];
    }
}
```