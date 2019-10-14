# LeetCode_647_回文子串
## 题目
给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。

示例 1:
```
输入: "abc"
输出: 3
解释: 三个回文子串: "a", "b", "c".
```
示例 2:
```
输入: "aaa"
输出: 6
说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
```
注意:
```
输入的字符串长度不会超过1000。
```
## 解法
### 思路
中心扩散
- 遍历字符串，以当前元素为中心扩散
- 扩散过程中判断当前范围的字符串是否是回文串，是的话就累加，否则终止
    - 字符串总长度为奇数
    - 字符串总长度为偶数
### 代码
```java
class Solution {
    private int sum;

    public int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            spread(s, i, i);
            spread(s, i, i + 1);
        }
        return sum;
    }

    private void spread(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            sum++;
            start--;
            end++;
        }
    }
}
```
## 解法二
### 思路
动态规划：
- dp[i][j]存储字符串中起始i结尾j范围的子串是否是回文
- 状态转移方程：dp[i][j] = s[i] == s[j] && (dp[i + 1][j - 1] || j - i <= 2)(如果子字符串长度不超过2，那两个字符相等的情况下就一定是回文串)
- base case：`i == j`的情况下都是true
### 代码
```java
class Solution {
    public int countSubstrings(String s) {
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        int count = 0;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
                if(dp[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
}
```