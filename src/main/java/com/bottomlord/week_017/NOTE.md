# LeetCode_1016_子串能表示从1到N数字的二进制串
## 题目
给定一个二进制字符串 S（一个仅由若干 '0' 和 '1' 构成的字符串）和一个正整数 N，如果对于从 1 到 N 的每个整数 X，其二进制表示都是 S 的子串，就返回 true，否则返回 false。

示例 1：
```
输入：S = "0110", N = 3
输出：true
```
示例 2：
```
输入：S = "0110", N = 4
输出：false
```
提示：
```
1 <= S.length <= 1000
1 <= N <= 10^9
```
## 解法
### 思路
- 遍历1到N
- 将数字转成二进制字符串
- 判断是否contains，如果不是返回false
- 遍历结束返回true
### 代码
```java
class Solution {
    public boolean queryString(String S, int N) {
        for (int i = 1; i <= N; i++) {
            String n = Integer.toBinaryString(i);
            if (!S.contains(n)) {
                return false;
            }
        }
        return true;
    }
}
```