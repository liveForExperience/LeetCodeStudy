# [LeetCode_2129_将标题首字母大写](https://leetcode.cn/problems/capitalize-the-title)
## 解法
### 思路
- 使用2个指针
  - 指针`i`，用来移动，搜索单词的边界，也就是当前字符是否是空格
  - 指针`j`，用来确定单词的起始，基于该坐标进行大写的翻转操作
- 2层循环
  - 外层是基于坐标`i`进行整个字符串的搜索，退出条件是超出字符串边界
  - 内层是基于坐标`i`进行单词的搜索，退出条件是遇到空格，获取超出字符串边界
  - 当内层循环中断后，`j`就是单词子字符串的头坐标，`i`就是单词之后的那个空格坐标。计算2个坐标之间的距离是否超过2，就可以判断是否符合题目要求，是否需要翻转子字符串的第一个字符。
  - 最后，因为题目确定单词与单词之间只有1个空格，就将`j`设置为`i + 1`，使其锚定到下一个单词的起始坐标位置
- 循环结束后，返回修改后的字符串即可。
- 为了方便处理，可以将字符串改成字符数组，然后基于数组进行修改。
### 代码
```java
class Solution {
    public String capitalizeTitle(String title) {
        char[] cs = title.toCharArray();
        int i = 0, j = 0, n = cs.length;
        for (; i < n; i++) {
            while(i < n && !isBlank(cs[i])) {
                cs[i] = toLower(cs[i]);
                i++;
            }

            if (i - j > 2) {
                cs[j] = toUpper(cs[j]);
            }

            j = i + 1;
        }

        return new String(cs);
    }

    private boolean isBlank(char c) {
        return c == ' ' || c == '\0';
    }

    private boolean isUpper(char c) {
        return c >= 65 && c <= 90;
    }

    private boolean isLower(char c) {
        return c >= 97 && c <= 122;
    }

    private char toUpper(char c) {
        return isUpper(c) ? c : (char) (c - 32);
    }

    private char toLower(char c) {
        return isLower(c) ? c : (char) (c + 32);
    }
}
```