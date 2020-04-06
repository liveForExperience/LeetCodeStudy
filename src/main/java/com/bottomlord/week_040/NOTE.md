# LeetCode_72_编辑距离
## 题目
给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：
```
插入一个字符
删除一个字符
替换一个字符
```
示例 1：
```
输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
```
示例 2：
```
输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
```
## 解法
### 思路
动态规划：
- `A插入一个字符`等价于`B删除一个字符`
- `B插入一个字符`等价于`A删除一个字符`
- `A修改一个字符`等价于`B修改一个字符`
- `dp[i][j]`：
    - i代表A的前i个字符
    - j代表B的前j个字符
    - dp[i][j]代表A的前i个字符和B的前j个字符，需要保持一致时最小的修改次数
- 状态转移方程：
    - 如果A的第i个字符和B的第j个字符相同：`dp[i][j] = 1 + min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1] - 1)`
    - 如果A的第i个字符和B的第j个字符不相同：`dp[i][j] = 1 + min(dp[i][j - 1], dp[i - 1][j], dp[i - 1][j - 1])`
- 初始化：
    - `dp[i][0] = i`
    - `dp[0][j] = j`
### 代码
```java
class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int add1 = dp[i - 1][j] + 1, add2 = dp[i][j - 1] + 1;
                int edit = word1.charAt(i - 1) == word2.charAt(j - 1) ? dp[i - 1][j - 1] : dp[i - 1][j - 1] + 1;
                dp[i][j] = Math.min(add1, Math.min(add2, edit));
            }
        }

        return dp[len1][len2];
    }
}
```