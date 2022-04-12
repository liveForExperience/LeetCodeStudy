# [LeetCode_806_写字符串需要的行数](https://leetcode-cn.com/problems/number-of-lines-to-write-string/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int[] numberOfLines(int[] widths, String S) {
        char[] cs = S.toCharArray();
        int line = 1, count = 0;

        for (char c : cs) {
            count += widths[c - 'a'];
            if (count > 100) {
                count = widths[c - 'a'];
                line++;
            }
        }

        return new int[]{line, count};
    }
}
```