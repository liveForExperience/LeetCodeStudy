# [LeetCode_471_编码最短长度的字符串](https://leetcode-cn.com/problems/encode-string-with-shortest-length/)
## 解法
### 思路
- 首先解决的问题是，找到当前字符串的重复子字符串：
    - 如果字符串为s，那么将`s + s`，然后从第2个字符(下标为1的位置)开始找字符串s，并判断找到的坐标`p`是否与s的长度一致
        - 如果一致：说明当前字符串内不包含重复子字符串
        - 如果不一致：则`[0,p]`就是字符串`s`中的子字符串
    - 然后通过字符串长度n，除以子字符串的长度，就是s中包含重复子字符串的个数
- 然后定义动态规划：
    - `dp[i][j]`：`[i,j]`区间的字符串表达式，可能是原始字符串，也可能是编码过的字符串
    - 状态转移方程：`dp[i][j] = min(dp[i][j], dp[i][k] + dp[k + 1][j])`，看以k为中点分割成的2个字符串，拼接在一起的长度是否小于`dp[i][j]`的长度
    - 返回结果：`dp[i][n - 1]`，这个值代表s这个字符串的编码值
- 算法过程：
    - 定义dp集合，二维表的长宽都是字符串s的长度
    - 接着是循环体：
        - 外层定义要填充二维表的子字符串长度`len`，从1开始
        - 内层定义子字符串的起始坐标，需要注意退出条件是`i + len - 1 < n`
        - 然后通过最开始的算法求出当前字符串的最优表达式，设置为当前二维表坐标的值
        - 接着做状态转移，在当前子字符串中，寻找是否还有可能的编码可能，也就是子字符串的子字符串是否有编码过，从而长度变短了
        - 做第三层遍历，定义k值，k的起始值为内层的`i`坐标，退出条件为`k < i + len - 1`
        - 在第三层中，确定拼接的子字符串是否比内层求出的子字符串更短，如果是，就替换
### 代码
```java
class Solution {
        public String encode(String s) {
        int n = s.length();
        String[][] dp = new String[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                dp[i][i + len - 1] = getEncodeString(dp, s, i, i + len - 1);

                for (int k = i; k < i + len - 1; k++) {
                    String str = dp[i][k] + dp[k + 1][i + len - 1];
                    if (str.length() < dp[i][i + len - 1].length()) {
                        dp[i][i + len - 1] = str;
                    }
                }
            }
        }

        return dp[0][n - 1];
    }

    private String getEncodeString(String[][] dp, String s, int i, int j) {
        s = s.substring(i, j + 1);
        if (s.length() < 5) {
            return s;
        }

        int p = (s + s).indexOf(s, 1);
        if (p < s.length()) {
            int count = s.length() / p;
            return count + "[" + dp[i][i + p - 1] + "]";
        }
        return s;
    }
}
```